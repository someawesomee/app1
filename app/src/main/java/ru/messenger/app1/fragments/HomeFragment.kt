package ru.messenger.app1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.messenger.app1.databinding.FragmentHomeBinding
import ru.messenger.app1.models.Chat
import ru.messenger.app1.utils.ChatAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chats = arrayListOf<Chat>()
        chats.add(Chat(1, "2024-10-15 16:18:13", "MLteam", "DeepSeek плох", "ml_team"))
        chats.add(Chat(2, "2024-10-10 15:15:10", "DevOps", "Привет", "devops"))
        chats.add(Chat(3, "2023-08-54 03:00:04", "Business", "Какие деньги?", "business"))

        // Раньше было: val chatsList: RecyclerView = view.findViewById(R.id.chats)
        // Заменяем на binding.chats
        binding.chats.layoutManager = LinearLayoutManager(requireContext())
        binding.chats.adapter = ChatAdapter(chats, requireContext())
    }
}
