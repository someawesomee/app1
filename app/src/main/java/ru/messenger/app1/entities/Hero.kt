package ru.messenger.app1.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes")
data class Hero(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String?,
    val gender: String?,
    val culture: String?,
    val born: String?,
    val titles: List<String>?,
    val aliases: List<String>?,
    val playedBy: List<String>?
)