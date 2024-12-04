package ru.afilonov.app1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.afilonov.app1.R
import ru.afilonov.app1.models.Chat
import ru.afilonov.app1.utils.ChatAdapter

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chatsList: RecyclerView = view.findViewById(R.id.chats)
        val chats = arrayListOf<Chat>()

        chats.add(Chat(5, "2024-10-15 16:18:13", "Сервис", "Затяни болты...", "profile_5"))
        chats.add(Chat(4, "2024-10-10 15:15:10", "Васёк", "Привет", "profile_4"))
        chats.add(Chat(3, "2023-08-54 03:00:04", "Лебовски", "Какие деньги?", "profile_3"))
        chats.add(Chat(2, "2020-06-17 14:05:40", "РазДва", "Картина у нас", "profile_2"))
        chats.add(Chat(1, "2004-01-13 10:15:10", "Томми", "А что не так с трейлером?", "profile_1"))

        chatsList.layoutManager = LinearLayoutManager(requireContext())
        chatsList.adapter = ChatAdapter(chats, requireContext())
    }
}