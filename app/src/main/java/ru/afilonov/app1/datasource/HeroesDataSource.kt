package ru.afilonov.app1.datasource

import ru.afilonov.app1.models.ApiResponse

interface HeroesDataSource {
    suspend fun getHeroes(page: Int, pageSize: Int): List<ApiResponse>
}