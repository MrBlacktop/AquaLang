package com.example.aqualang.education.exercises.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.aqualang.R
import com.example.aqualang.education.exercises.ExerciseActivity
import com.example.aqualang.education.exercises.controlFragment.ExerciseControlViewModel
import com.example.aqualang.education.exercises.controlFragment.ExerciseControlViewModelFactory
import com.example.domain.useCases.LoadExercisesUseCase
import kotlinx.android.synthetic.main.exercise_result_fragment.*
import javax.inject.Inject

class ExerciseResultFragment : Fragment() {


    private lateinit var viewModel: ExerciseControlViewModel

    @Inject
    lateinit var loadExercisesUseCase: LoadExercisesUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val exerciseActivity = requireActivity() as ExerciseActivity

        exerciseActivity.exerciseComponent.inject(this)
        val viewModelFactory = ExerciseControlViewModelFactory(
            loadExercisesUseCase,
            exerciseActivity.lessonId
        )

        viewModel = ViewModelProvider(
            exerciseActivity,
            viewModelFactory
        ).get(ExerciseControlViewModel::class.java)

        return inflater.inflate(R.layout.exercise_result_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        scoreTextView.text = viewModel.submittedAnswers.filter { it.isCorrect }.size.toString()
        allExercisesTextView.text = viewModel.exercises.value!!.size.toString()

        super.onViewCreated(view, savedInstanceState)
        doneButton.setOnClickListener {
            viewModel.sendAnswers()
            requireActivity().finish()
        }
    }
}