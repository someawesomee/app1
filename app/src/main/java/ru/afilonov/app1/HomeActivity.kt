package ru.afilonov.app1

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val chatsList: RecyclerView = findViewById(R.id.chats)
        val chats = arrayListOf<Chat>()

        chats.add(Chat(5, "2024-10-15 16:18:13", "Сервис", "Затяни болты...", "profile_5"))
        chats.add(Chat(4, "2024-10-10 15:15:10", "Васёк", "Привет", "profile_4"))
        chats.add(Chat(3, "2023-08-54 03:00:04", "Лебовски", "Какие деньги?", "profile_3"))
        chats.add(Chat(2, "2020-06-17 14:05:40", "РазДва", "Картина у нас", "profile_2"))
        chats.add(Chat(1, "2004-01-13 10:15:10", "Томми", "А что не так с трейлером?", "profile_1"))

        chatsList.layoutManager = LinearLayoutManager(this)
        chatsList.adapter = ChatAdapter(chats, this)

    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
}