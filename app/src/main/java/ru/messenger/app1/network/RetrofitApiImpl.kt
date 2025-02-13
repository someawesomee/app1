package ru.messenger.app1.network
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import ru.messenger.app1.models.ApiResponse

class RetrofitApiImpl : ru.messenger.app1.network.RetrofitApi {
    private val NETWORK_BASE_URL = "https://www.anapioficeandfire.com"

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val networkApi: ru.messenger.app1.network.RetrofitApi by lazy {
        Retrofit.Builder()
            .baseUrl(NETWORK_BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(ru.messenger.app1.network.RetrofitApi::class.java)
    }

    override suspend fun getHeroes(page: Int, pageSize: Int): List<ru.messenger.app1.models.ApiResponse> = networkApi.getHeroes(page, pageSize)
}