package ru.messenger.app1.datasource

import ru.messenger.app1.models.ApiResponse
import ru.messenger.app1.network.RetrofitApi

class RemoteHeroesDataSource(private val api: RetrofitApi) : HeroesDataSource {
    override suspend fun getHeroes(page: Int, pageSize: Int): List<ru.messenger.app1.models.ApiResponse> {
        return api.getHeroes(page, pageSize)
    }
}