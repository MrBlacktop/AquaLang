package com.example.aqualang.education.exercises

import android.os.Bundle
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
import javax.inject.Inject


class SingleOptionExerciseFragment : Fragment() {

    private lateinit var viewModel: ExerciseControlViewModel

    @Inject
    lateinit var loadExercisesUseCase: LoadExercisesUseCase

    private lateinit var exercise: Exercise

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSingleOptionExerciseBinding.inflate(inflater, container, false)

        (requireActivity() as ExerciseActivity).exerciseComponent.inject(this)

        val viewModelFactory = ExerciseControlViewModelFactory(loadExercisesUseCase, null)
        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        ).get(ExerciseControlViewModel::class.java)

        val exerciseIndex = requireArguments().getInt(EXERCISE_INDEX)
        viewModel.exercises.value?.let {
            exercise = it[exerciseIndex]
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    companion object {
        private const val EXERCISE_INDEX = "EXERCISE_INDEX"

        fun newInstance(exerciseIndex: Int) =
            SingleOptionExerciseFragment().apply {
                arguments = bundleOf(EXERCISE_INDEX to exerciseIndex)
            }

    }

}