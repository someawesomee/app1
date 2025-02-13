package ru.messenger.app1.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.messenger.app1.databinding.ListItemBinding
import ru.messenger.app1.models.ApiResponse

class ApiResponseAdapter(private var items: List<ApiResponse>) : RecyclerView.Adapter<ApiResponseAdapter.ApiResponseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiResponseViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApiResponseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApiResponseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size


    inner class ApiResponseViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(apiResponse: ApiResponse) {
            "name: ${if (apiResponse.name.isNullOrEmpty()) "Unknown" else apiResponse.name}".also {
                binding.tvName.text = it
            }
            "gender: ${if (apiResponse.gender.isNullOrEmpty()) "Unknown" else apiResponse.gender}".also {
                binding.tvGender.text = it
            }
            "culture: ${if (apiResponse.culture.isNullOrEmpty()) "Unknown" else apiResponse.culture}".also {
                binding.tvCulture.text = it
            }
            "born: ${if (apiResponse.born.isNullOrEmpty()) "Unknown" else apiResponse.born}".also {
                binding.tvBorn.text = it
            }
            "titles: ${
                if (apiResponse.titles.isNullOrEmpty()) "None" else apiResponse.titles.joinToString(
                    ", "
                )
            }".also { binding.tvTitles.text = it }
            "aliases: ${
                if (apiResponse.aliases.isNullOrEmpty()) "None" else apiResponse.aliases.joinToString(
                    ", "
                )
            }".also { binding.tvAliases.text = it }
            "playedBy: ${
                if (apiResponse.playedBy.isNullOrEmpty()) "None" else apiResponse.playedBy.joinToString(
                    ", "
                )
            }".also { binding.tvPlayedBy.text = it }
        }
    }

}