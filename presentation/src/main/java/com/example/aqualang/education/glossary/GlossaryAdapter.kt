package com.example.aqualang.education.glossary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aqualang.databinding.GlossaryListItemBinding
import com.example.domain.models.GlossaryWord
import javax.inject.Inject

class GlossaryAdapter @Inject constructor() : RecyclerView.Adapter<GlossaryAdapter.ViewHolder>() {
    var data = listOf<GlossaryWord>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: GlossaryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GlossaryWord) {
            binding.glossaryWord = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GlossaryListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


}