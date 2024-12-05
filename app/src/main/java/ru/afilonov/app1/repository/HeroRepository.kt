package ru.afilonov.app1.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.afilonov.app1.dao.HeroDao
import ru.afilonov.app1.entities.Hero

class HeroRepository(private val heroDao: HeroDao) {

    fun getCharacters(page: Int, pageSize: Int): Flow<List<Hero>> {
        val offset = (page - 1) * pageSize
        return heroDao.getCharacters(pageSize, offset)
    }

    suspend fun saveCharacters(characters: List<Hero>) {
        heroDao.insertCharacters(characters)
    }

    suspend fun clearCharacters() {
        withContext(Dispatchers.IO) {
            heroDao.deleteAll()
        }
    }
}