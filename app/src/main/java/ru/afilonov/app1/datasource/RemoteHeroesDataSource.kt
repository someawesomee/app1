package ru.afilonov.app1.datasource

import ru.afilonov.app1.models.ApiResponse
import ru.afilonov.app1.network.RetrofitApi

class RemoteHeroesDataSource(private val api: RetrofitApi) : HeroesDataSource {
    override suspend fun getHeroes(page: Int, pageSize: Int): List<ApiResponse> {
        return api.getHeroes(page, pageSize)
    }
}