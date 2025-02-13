package ru.messenger.app1.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.messenger.app1.models.User

class SharedViewModel : ViewModel() {
    val userLiveData = MutableLiveData<ru.messenger.app1.models.User>()
}