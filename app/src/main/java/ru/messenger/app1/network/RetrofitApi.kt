package ru.messenger.app1.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.messenger.app1.models.ApiResponse

interface RetrofitApi {
    @GET(value = "/api/characters")
    suspend fun getHeroes(
        @Query("page") page: Int,
        @Query("pageSize") pageSize : Int
    ): List<ApiResponse>
}
