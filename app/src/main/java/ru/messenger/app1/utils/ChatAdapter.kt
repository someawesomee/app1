package ru.messenger.app1.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.messenger.app1.R
import ru.messenger.app1.models.Chat

class ChatAdapter(var chats: List<Chat>, var context: Context) : RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.profile_image)
        val sender: TextView = view.findViewById(R.id.sender_name)
        val date: TextView = view.findViewById(R.id.last_date_of_message)
        val message: TextView = view.findViewById(R.id.content_of_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chats.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.sender.text = chats[position].senderName
        holder.date.text = chats[position].dateOfLastMessage
        holder.message.text = chats[position].lastMessage

        val imageId = context.resources.getIdentifier(
            chats[position].image,
            "drawable",
            context.packageName
        )

        holder.image.setImageResource(imageId)
    }
}