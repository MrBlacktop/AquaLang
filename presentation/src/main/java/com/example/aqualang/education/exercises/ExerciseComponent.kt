package com.example.aqualang.education.exercises

import com.example.aqualang.education.exercises.controlFragment.ExerciseControlFragment
import com.example.aqualang.education.exercises.result.ExerciseResultFragment
import dagger.Subcomponent

@Subcomponent
interface ExerciseComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(): ExerciseComponent
    }

    fun inject(exerciseControlFragment: ExerciseControlFragment)
    fun inject(exerciseResultFragment: ExerciseResultFragment)
    fun inject(singleOptionExerciseFragment: SingleOptionExerciseFragment)
    fun inject(multipleOptionExerciseFragment: MultipleOptionExerciseFragment)
    fun inject(textInputExerciseFragment: TextInputExerciseFragment)
}