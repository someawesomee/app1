package ru.messenger.app1.utils

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import ru.messenger.app1.models.ApiResponse

class MyDiffCallback(
    private val oldList: List<ApiResponse>,
    private val newList: List<ApiResponse>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size
}