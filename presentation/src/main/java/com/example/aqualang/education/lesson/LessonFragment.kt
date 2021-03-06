package com.example.aqualang.education.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aqualang.AquaLangApplication
import com.example.aqualang.R
import com.example.aqualang.databinding.LessonFragmentBinding
import com.example.aqualang.education.exercises.ExerciseActivity
import com.example.domain.interactors.LessonInteractor
import javax.inject.Inject

class LessonFragment : Fragment() {

    companion object {
        private const val LESSON_ID = "LESSON_ID"

        fun createBundleWithIndex(lessonId: Int): Bundle {
            return bundleOf(LESSON_ID to lessonId)
        }
    }

    @Inject
    lateinit var lessonInteractor: LessonInteractor

    private lateinit var viewModel: LessonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LessonFragmentBinding.inflate(inflater, container, false)

        (requireActivity().application as AquaLangApplication).appComponent.inject(this)

        val lessonId = requireArguments().getInt(LESSON_ID)
        val viewModelFactory = LessonViewModelFactory(lessonInteractor, lessonId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LessonViewModel::class.java)

        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToExercises.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    this.findNavController().navigate(
                        R.id.action_lessonFragment_to_exerciseActivity,
                        ExerciseActivity.createBundleWithId(viewModel.lesson.id)
                    )
                    viewModel.doneNavigationToExercises()
                }
            }
        })
    }


}