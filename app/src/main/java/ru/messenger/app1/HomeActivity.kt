package ru.messenger.app1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.messenger.app1.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val chatsList: RecyclerView = findViewById(R.id.chats)
        val chats = arrayListOf<Chat>()

        chats.add(Chat(1, "2024-10-15 16:18:13", "MLteam", "DeepSeek плох", "ml_team"))
        chats.add(Chat(2, "2024-10-10 15:15:10", "DevOps", "Привет", "devops"))
        chats.add(Chat(3, "2023-08-54 03:00:04", "Business", "Какие деньги?", "business"))

        chatsList.layoutManager = LinearLayoutManager(this)
        chatsList.adapter = ChatAdapter(chats, this)

    }
}