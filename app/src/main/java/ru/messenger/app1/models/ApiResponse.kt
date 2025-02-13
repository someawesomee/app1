package ru.messenger.app1.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse (
    val name: String?,
    val gender: String?,
    val culture: String?,
    val born: String?,
    val titles: List<String>?,
    val aliases: List<String>?,
    val playedBy: List<String>?
)
