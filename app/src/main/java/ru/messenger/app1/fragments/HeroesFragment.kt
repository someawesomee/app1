package ru.messenger.app1.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.messenger.app1.databinding.FragmentHeroesBinding
import ru.messenger.app1.datasource.HeroesDataSource
import ru.messenger.app1.datasource.RemoteHeroesDataSource
import ru.messenger.app1.db.AppDatabase
import ru.messenger.app1.entities.Hero
import ru.messenger.app1.models.ApiResponse
import ru.messenger.app1.network.RetrofitApi
import ru.messenger.app1.network.RetrofitApiImpl
import ru.messenger.app1.repository.HeroRepository
import ru.messenger.app1.utils.ApiResponseAdapter

class HeroesFragment : Fragment() {

    private var _retrofitApi: RetrofitApi? = null
    private val retrofitApi get() = _retrofitApi ?: throw Exception()

    private var _binding: FragmentHeroesBinding? = null
    private val binding get() = _binding ?: throw Exception()
    private lateinit var heroRepository: HeroRepository

    private var currentPage = 1
    private val pageSize = 10

    private lateinit var adapter: ApiResponseAdapter
    private lateinit var dataSource: HeroesDataSource
    private var hasMoreData = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _retrofitApi = RetrofitApiImpl()
        dataSource = RemoteHeroesDataSource(retrofitApi)
        val heroDao = AppDatabase.getInstance(requireContext()).heroDao()
        heroRepository = HeroRepository(heroDao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateButtonsVisibility()

        binding.recyclerViewHeroes.layoutManager = LinearLayoutManager(requireContext())
        adapter = ApiResponseAdapter(listOf())
        binding.recyclerViewHeroes.adapter = adapter

        loadData()

        binding.refreshButton.setOnClickListener {
            refreshData()
        }

        binding.nextPageButton.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val heroes = dataSource.getHeroes(currentPage + 1, pageSize)
                    if (heroes.isNotEmpty()) {
                        heroRepository.saveCharacters(heroes.map { apiResponse ->
                            Hero(
                                name = apiResponse.name,
                                gender = apiResponse.gender,
                                culture = apiResponse.culture,
                                born = apiResponse.born,
                                titles = apiResponse.titles,
                                aliases = apiResponse.aliases,
                                playedBy = apiResponse.playedBy
                            )
                        })
                        currentPage++
                        loadData()
                    }
                } catch (e: Exception) {
                    Log.e("HeroesFragment", "Failed to fetch next page from API", e)
                }
            }
        }

        binding.previousPageButton.setOnClickListener {
            if (currentPage > 1) {
                currentPage--
                loadData()
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                    view content
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun loadData() {
        lifecycleScope.launch {
            heroRepository.getCharacters(currentPage, pageSize).collect { heroes ->
                val apiResponses = heroes.map { hero ->
                    ApiResponse(
                        name = hero.name,
                        gender = hero.gender,
                        culture = hero.culture,
                        born = hero.born,
                        titles = hero.titles,
                        aliases = hero.aliases,
                        playedBy = hero.playedBy
                    )
                }
                adapter.updateData(apiResponses)
                hasMoreData = heroes.size == pageSize
                updateButtonsVisibility()
            }
        }
    }

    private fun fetchHeroesFromApi() {
        lifecycleScope.launch {
            try {
                val heroes = dataSource.getHeroes(currentPage, pageSize)
                Log.d("HeroesFragment", "Fetched heroes from API: $heroes")

                if (heroes.isNotEmpty()) {
                    heroRepository.saveCharacters(heroes.map { apiResponse ->
                        Hero(
                            name = apiResponse.name,
                            gender = apiResponse.gender,
                            culture = apiResponse.culture,
                            born = apiResponse.born,
                            titles = apiResponse.titles,
                            aliases = apiResponse.aliases,
                            playedBy = apiResponse.playedBy
                        )
                    })

                    adapter.updateData(heroes)
                    hasMoreData = heroes.size == pageSize
                }

                updateButtonsVisibility()

            } catch (e: Exception) {
                Log.e("HeroesFragment", "Failed to fetch heroes from API", e)
            }
        }
    }

    private fun updateButtonsVisibility() {
        binding.previousPageButton.visibility = if (currentPage > 1) View.VISIBLE else View.GONE
        binding.nextPageButton.visibility = if (hasMoreData) View.VISIBLE else View.GONE
    }


    private fun refreshData() {
        lifecycleScope.launch {
            currentPage = 1
            heroRepository.clearCharacters()
            fetchHeroesFromApi()
        }
    }
}