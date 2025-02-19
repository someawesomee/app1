package ru.messenger.app1.models

data class Chat(
    val id: Int,
    val dateOfLastMessage: String,
    val senderName: String,
    val lastMessage: String,
    val image: String
)
