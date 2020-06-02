package com.example.aqualang.education.coursesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aqualang.databinding.CourseListItemBinding
import com.example.aqualang.generated.callback.OnClickListener
import com.example.domain.models.Course

class CourseAdapter(private val clickListener: CourseListener) :
    RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    var data = listOf<Course>()
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
        holder.bind(item,clickListener)
    }

    class ViewHolder private constructor(val binding: CourseListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Course,
            clickListener: CourseListener
        ) {
            binding.course = item
            binding.root.setOnClickListener {
                clickListener.onClick(item.id)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CourseListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    class CourseListener(val clickListener: (courseId: Int) -> Unit) {
        fun onClick(courseId: Int) = clickListener(courseId)
    }
}