package com.example.aqualang.education.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aqualang.databinding.TopicListItemBinding
import com.example.domain.models.Course
import com.example.domain.models.Topic

class TopicAdapter(private val course: Course, private val clickListener: TopicListener) :
    RecyclerView.Adapter<TopicAdapter.ViewHolder>() {
    var data = listOf<Topic>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(course, item, clickListener)
    }

    class ViewHolder private constructor(val binding: TopicListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            course: Course,
            item: Topic,
            clickListener: TopicListener
        ) {
            binding.topic = item
            if (course.isActive) {
                binding.root.setOnClickListener { clickListener.onClick(item.id) }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TopicListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }

    class TopicListener(val clickListener: (topicId: Int) -> Unit) {
        fun onClick(topicId: Int) = clickListener(topicId)
    }


}