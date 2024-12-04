package ru.afilonov.app1.fragments

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.afilonov.app1.databinding.FragmentHeroesBinding
import ru.afilonov.app1.datasource.HeroesDataSource
import ru.afilonov.app1.datasource.RemoteHeroesDataSource
import ru.afilonov.app1.network.RetrofitApi
import ru.afilonov.app1.network.RetrofitApiImpl
import ru.afilonov.app1.utils.ApiResponseAdapter
import java.io.File
import java.io.IOException

class HeroesFragment : Fragment() {

    private var _retrofitApi: RetrofitApi? = null
    private val retrofitApi get() = _retrofitApi ?: throw Exception()

    private var _binding: FragmentHeroesBinding? = null
    private val binding get() = _binding ?: throw Exception()

    private lateinit var adapter: ApiResponseAdapter
    private lateinit var dataSource: HeroesDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _retrofitApi = RetrofitApiImpl()
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
                val heroes = dataSource.getHeroes(12, 50)
                Log.d("HeroesFragment", "Fetched heroes: $heroes")
                binding.recyclerViewHeroes.adapter = ApiResponseAdapter(heroes)

                val isSaved = saveFileToExternalStorage("heroes_12.txt", heroes.toString())
                if (isSaved) {
                    Log.d("HeroesFragment", "Heroes data saved to file successfully")
                } else {
                    Log.e("HeroesFragment", "Failed to save heroes data to file")
                }

                saveFileToExternalStorage("file_12.txt", heroes.toString())
            } catch (e: Exception) {
                Log.e("HeroesFragment", "Failed to fetch heroes", e)
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                    Save to txt
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun saveFileToExternalStorage(fileName: String, content: String): Boolean {
        val externalDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        if (externalDir != null) {
            val file = File(externalDir, fileName)
            return try {
                file.writeText(content)
                true
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        }
        return false
    }
}