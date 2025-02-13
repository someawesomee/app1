package ru.messenger.app1.models

import java.io.Serializable
import ru.messenger.app1.models.User


class User(val login: String, val email: String, val pass: String) : Serializable {
}