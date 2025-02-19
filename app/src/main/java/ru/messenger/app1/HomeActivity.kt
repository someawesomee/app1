package ru.messenger.app1

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val userLogin = intent.getStringExtra("USER_LOGIN")
        val userEmail = intent.getStringExtra("USER_EMAIL")

        // Можно вывести Toast или установить TextView
        Toast.makeText(this, "Регистрация успешна! Добро пожаловать, $userLogin", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Добро пожаловать, $userLogin", Toast.LENGTH_SHORT).show()

        val chatsList: RecyclerView = findViewById(R.id.chats)
        val chats = arrayListOf<Chat>()


        chats.add(Chat(1, "2024-10-15 16:18:13", "MLteam", "DeepSeek плох", "ml_team"))
        chats.add(Chat(2, "2024-10-10 15:15:10", "DevOps", "Привет", "devops"))
        chats.add(Chat(3, "2023-08-54 03:00:04", "Business", "Какие деньги?", "business"))

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