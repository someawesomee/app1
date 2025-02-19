package ru.messenger.app1.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.messenger.app1.R
import ru.messenger.app1.databinding.ChatInListBinding
import ru.messenger.app1.models.Chat

class ChatAdapter(
    private val chats: List<Chat>,
    private val context: Context
) : RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ChatInListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ChatInListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = chats.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val chat = chats[position]
        with(holder.binding) {
            senderName.text = chat.senderName
            lastDateOfMessage.text = chat.dateOfLastMessage
            contentOfMessage.text = chat.lastMessage

            val imageId = context.resources.getIdentifier(
                chat.image,
                "drawable",
                context.packageName
            )
            profileImage.setImageResource(imageId)
        }
    }
}
