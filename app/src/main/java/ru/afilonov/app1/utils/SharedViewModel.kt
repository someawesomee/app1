package ru.afilonov.app1.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.afilonov.app1.models.User

class SharedViewModel : ViewModel() {
    val userLiveData = MutableLiveData<User>()
}