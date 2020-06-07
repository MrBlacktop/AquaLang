package com.example.aqualang.education.exercises.controlFragment.viewPager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aqualang.education.exercises.MultipleOptionExerciseFragment
import com.example.aqualang.education.exercises.SingleOptionExerciseFragment
import com.example.aqualang.education.exercises.TextInputExerciseFragment
import com.example.domain.models.exercise.Exercise
import com.example.domain.models.exercise.ExerciseType

class ExerciseAdapter(activity: AppCompatActivity, private val exercises: List<Exercise>): FragmentStateAdapter(activity) {
    override fun getItemCount() = exercises.size

    override fun createFragment(position: Int): Fragment {
        return when(exercises[position].type){
            ExerciseType.SINGLE_OPTION -> SingleOptionExerciseFragment.newInstance(position)
            ExerciseType.MULTIPLE_OPTIONS -> MultipleOptionExerciseFragment()
            ExerciseType.TEXT_INPUT -> TextInputExerciseFragment()
        }
    }
}