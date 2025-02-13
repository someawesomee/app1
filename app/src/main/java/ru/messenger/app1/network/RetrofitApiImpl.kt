package ru.messenger.app1.network
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import ru.messenger.app1.models.ApiResponse

class RetrofitApiImpl : RetrofitApi {
    private val NETWORK_BASE_URL = "https://www.anapioficeandfire.com"

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val networkApi: RetrofitApi by lazy {
        Retrofit.Builder()
            .baseUrl(NETWORK_BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(RetrofitApi::class.java)
    }

    override suspend fun getHeroes(page: Int, pageSize: Int): List<ApiResponse> = networkApi.getHeroes(page, pageSize)
}