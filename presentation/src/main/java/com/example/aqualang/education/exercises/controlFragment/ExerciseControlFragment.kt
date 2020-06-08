package com.example.aqualang.education.exercises.controlFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aqualang.AquaLangApplication
import com.example.aqualang.R
import com.example.aqualang.education.exercises.ExerciseActivity
import com.example.aqualang.education.exercises.controlFragment.viewPager.ExerciseAdapter
import com.example.domain.useCases.LoadExercisesUseCase
import kotlinx.android.synthetic.main.exercise_control_fragment.*
import javax.inject.Inject

class ExerciseControlFragment : Fragment() {


    private lateinit var viewModel: ExerciseControlViewModel

    @Inject
    lateinit var useCase: LoadExercisesUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireActivity() as ExerciseActivity

        activity.exerciseComponent.inject(this)

        val lessonId = activity.lessonId

        val viewModelFactory = ExerciseControlViewModelFactory(useCase, lessonId)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(ExerciseControlViewModel::class.java)

        return inflater.inflate(R.layout.exercise_control_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.exercises.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    exerciseViewPager.adapter =
                        ExerciseAdapter(
                            requireActivity() as AppCompatActivity,
                            it
                        )
                }
            }
        })

        viewModel.showNetworkErrorToast.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show()
                    viewModel.doneShowingErrorToast()
                }
            }
        })
    }
}