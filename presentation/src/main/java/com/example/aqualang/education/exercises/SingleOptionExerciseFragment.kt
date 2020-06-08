package com.example.aqualang.education.exercises

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.aqualang.AquaLangApplication
import com.example.aqualang.R
import com.example.aqualang.databinding.FragmentSingleOptionExerciseBinding
import com.example.aqualang.education.exercises.controlFragment.ExerciseControlViewModel
import com.example.aqualang.education.exercises.controlFragment.ExerciseControlViewModelFactory
import com.example.domain.models.exercise.Exercise
import com.example.domain.useCases.LoadExercisesUseCase
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_single_option_exercise.*
import java.lang.Exception
import javax.inject.Inject


class SingleOptionExerciseFragment : Fragment() {

    private lateinit var viewModel: ExerciseControlViewModel

    @Inject
    lateinit var loadExercisesUseCase: LoadExercisesUseCase

    private lateinit var binding: FragmentSingleOptionExerciseBinding

    private lateinit var exercise: Exercise

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleOptionExerciseBinding.inflate(inflater, container, false)

        val exerciseActivity = requireActivity() as ExerciseActivity
        exerciseActivity.exerciseComponent.inject(this)

        val viewModelFactory =
            ExerciseControlViewModelFactory(loadExercisesUseCase, exerciseActivity.lessonId)
        viewModel = ViewModelProvider(
            exerciseActivity,
            viewModelFactory
        ).get(ExerciseControlViewModel::class.java)

        val exerciseIndex = requireArguments().getInt(EXERCISE_INDEX)
        exercise = viewModel.exercises.value!![exerciseIndex]

        if (exercise.answers.size != 4) throw Exception("Wrong answers count (must be 4)")

        binding.exercise = exercise
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            val answers = exercise.answers
            val isCorrect = when (binding.radioGroup.checkedRadioButtonId) {
                R.id.firstAnswerRadioButton -> answers[0].isCorrect
                R.id.secondAnswerRadioButton -> answers[1].isCorrect
                R.id.thirdAnswerRadioButton -> answers[3].isCorrect
                R.id.fourthAnswerRadioButton -> answers[4].isCorrect
                else -> false
            }
            viewModel.registerAnswer(isCorrect, exercise.id)
            binding.submitButton.visibility = View.GONE
        }

    }


    companion object {
        private const val EXERCISE_INDEX = "EXERCISE_INDEX"

        fun newInstance(exerciseIndex: Int) =
            SingleOptionExerciseFragment().apply {
                arguments = bundleOf(EXERCISE_INDEX to exerciseIndex)
            }
    }

}