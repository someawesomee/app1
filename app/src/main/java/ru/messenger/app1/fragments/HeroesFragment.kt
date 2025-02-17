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
import ru.messenger.app1.network.RetrofitApi
import ru.messenger.app1.network.RetrofitApiImpl
import ru.messenger.app1.utils.ApiResponseAdapter

class HeroesFragment : Fragment() {

    private var _retrofitApi: RetrofitApi? = null
    private val retrofitApi get() = _retrofitApi!!

    private var _binding: FragmentHeroesBinding? = null
    private val binding get() = _binding ?: throw Exception()

    private lateinit var adapter: ApiResponseAdapter
    private lateinit var dataSource: HeroesDataSource


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _retrofitApi = ru.messenger.app1.network.RetrofitApiImpl()
        dataSource = RemoteHeroesDataSource(retrofitApi)
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

        binding.recyclerViewHeroes.layoutManager = LinearLayoutManager(requireContext())

        adapter = ApiResponseAdapter(listOf())
        binding.recyclerViewHeroes.adapter = adapter

        lifecycleScope.launch {
            try {
                val heroes = dataSource.getHeroes(4, 50)
                Log.d("HeroesFragment", "Fetched heroes: $heroes")
                binding.recyclerViewHeroes.adapter = ApiResponseAdapter(heroes)
            } catch (e: Exception) {
                Log.e("HeroesFragment", "Failed to fetch heroes", e)
            }
        }
    }
}