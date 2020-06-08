package com.example.aqualang.education.exercises

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.aqualang.R
import com.example.aqualang.databinding.FragmentMultipleOptionExerciseBinding
import com.example.aqualang.education.exercises.controlFragment.ExerciseControlViewModel
import com.example.aqualang.education.exercises.controlFragment.ExerciseControlViewModelFactory
import com.example.domain.models.exercise.Exercise
import com.example.domain.useCases.LoadExercisesUseCase
import java.lang.Exception
import javax.inject.Inject


class MultipleOptionExerciseFragment : Fragment() {

    @Inject
    lateinit var loadExerciseUseCase: LoadExercisesUseCase

    private lateinit var viewModel: ExerciseControlViewModel

    private lateinit var binding: FragmentMultipleOptionExerciseBinding

    private lateinit var exercise: Exercise

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         binding = FragmentMultipleOptionExerciseBinding.inflate(inflater, container, false)

        val exerciseActivity = requireActivity() as ExerciseActivity
        exerciseActivity.exerciseComponent.inject(this)

        val viewModelFactory =
            ExerciseControlViewModelFactory(loadExerciseUseCase, exerciseActivity.lessonId)
        viewModel = ViewModelProvider(
            exerciseActivity,
            viewModelFactory
        ).get(ExerciseControlViewModel::class.java)

        val exerciseId = requireArguments().getInt(EXERCISE_INDEX)
        exercise =viewModel.exercises.value!![exerciseId]
        if(exercise.answers.size != 8) throw Exception()

        binding.exercise = exercise
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            var isCorrect = true
            val answers = exercise.answers

            isCorrect = isCorrect && (binding.firstAnswer.isChecked == answers[0].isCorrect)
            isCorrect = isCorrect && (binding.secondAnswer.isChecked == answers[1].isCorrect)
            isCorrect = isCorrect && (binding.thirdAnswer.isChecked == answers[2].isCorrect)
            isCorrect = isCorrect && (binding.fourthAnswer.isChecked == answers[3].isCorrect)
            isCorrect = isCorrect && (binding.fifthAnswer.isChecked == answers[4].isCorrect)
            isCorrect = isCorrect && (binding.sixthAnswer.isChecked == answers[5].isCorrect)
            isCorrect = isCorrect && (binding.seventhAnswer.isChecked == answers[6].isCorrect)
            isCorrect = isCorrect && (binding.eighthAnswer.isChecked == answers[7].isCorrect)

            viewModel.registerAnswer(isCorrect, exercise.id)

            binding.submitButton.visibility = View.GONE
        }
    }

    companion object {
        private const val EXERCISE_INDEX = "EXERCISE_INDEX"

        fun newInstance(exerciseIndex: Int) =
            MultipleOptionExerciseFragment().apply {
                arguments = bundleOf(EXERCISE_INDEX to exerciseIndex)
            }
    }

}