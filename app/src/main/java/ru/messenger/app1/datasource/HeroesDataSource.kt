package ru.messenger.app1.datasource

import ru.messenger.app1.models.ApiResponse

interface HeroesDataSource {
    suspend fun getHeroes(page: Int, pageSize: Int): List<ApiResponse>
}