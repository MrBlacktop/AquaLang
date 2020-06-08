package com.example.aqualang.education.exercises

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.aqualang.R
import com.example.aqualang.databinding.FragmentTextInputExerciseBinding
import com.example.aqualang.education.exercises.controlFragment.ExerciseControlViewModel
import com.example.aqualang.education.exercises.controlFragment.ExerciseControlViewModelFactory
import com.example.domain.models.exercise.Answer
import com.example.domain.models.exercise.Exercise
import com.example.domain.useCases.LoadExercisesUseCase
import java.lang.Exception
import javax.inject.Inject

class TextInputExerciseFragment : Fragment() {

    @Inject
    lateinit var loadExercisesUserCase: LoadExercisesUseCase

    private lateinit var viewModel: ExerciseControlViewModel

    private lateinit var binding: FragmentTextInputExerciseBinding

    private lateinit var answer: Answer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTextInputExerciseBinding.inflate(inflater, container, false)

        val exerciseActivity = requireActivity() as ExerciseActivity
        exerciseActivity.exerciseComponent.inject(this)

        val viewModelFactory =
            ExerciseControlViewModelFactory(loadExercisesUserCase, exerciseActivity.lessonId)
        viewModel = ViewModelProvider(
            exerciseActivity,
            viewModelFactory
        ).get(ExerciseControlViewModel::class.java)

        val exerciseId = requireArguments().getInt(EXERCISE_INDEX)
        val exercise = viewModel.exercises.value!![exerciseId]

        if (exercise.answers.size != 1) throw Exception("")
        answer = exercise.answers[0]

        binding.exercise = exercise


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            val isCorrect = binding.textInputExercise.text.toString() == answer.content
            viewModel.registerAnswer(isCorrect,answer.exerciseId)
            binding.submitButton.visibility = View.GONE
        }
    }

    companion object {
        private const val EXERCISE_INDEX = "EXERCISE_INDEX"

        fun newInstance(exerciseIndex: Int) =
            TextInputExerciseFragment().apply {
                arguments = bundleOf(EXERCISE_INDEX to exerciseIndex)
            }

    }
}