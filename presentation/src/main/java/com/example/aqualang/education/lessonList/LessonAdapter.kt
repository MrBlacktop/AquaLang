package com.example.aqualang.education.lessonList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aqualang.databinding.LessonListItemBinding
import com.example.domain.models.Lesson

class LessonAdapter(private val clickListener: LessonListener) :
    RecyclerView.Adapter<LessonAdapter.ViewHolder>() {
    var data = listOf<Lesson>()
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
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(val binding: LessonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Lesson,
            clickListener: LessonListener
        ) {
            binding.lesson = item
            binding.root.setOnClickListener { clickListener.onClick(item.id) }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LessonListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class LessonListener(val clickListener: (lessonId: Int) -> Unit) {

        fun onClick(lessonId: Int) = clickListener(lessonId)
    }
}