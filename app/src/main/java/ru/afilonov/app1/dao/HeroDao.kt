package ru.afilonov.app1.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.afilonov.app1.entities.Hero

@Dao
interface HeroDao {

    @Query("SELECT * FROM heroes LIMIT :pageSize OFFSET :offset")
    fun getCharacters(pageSize: Int, offset: Int): Flow<List<Hero>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<Hero>)

    @Query("DELETE FROM heroes")
    fun deleteAll()
}