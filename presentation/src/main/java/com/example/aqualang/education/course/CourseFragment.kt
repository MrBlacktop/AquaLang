package com.example.aqualang.education.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.aqualang.AquaLangApplication
import com.example.aqualang.databinding.CourseFragmentBinding
import com.example.domain.interactors.CourseInteractor
import javax.inject.Inject

class CourseFragment : Fragment() {


    private lateinit var viewModel: CourseViewModel

    @Inject
    lateinit var courseInteractor: CourseInteractor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CourseFragmentBinding.inflate(inflater,container,false)
        (requireActivity().application as AquaLangApplication).appComponent.inject(this)

        val id = requireArguments().getInt(COURSE_ID)

        val viewModelFactory = CourseViewModelFactory(courseInteractor, id)

        viewModel = ViewModelProvider(this,viewModelFactory).get(CourseViewModel::class.java)

        binding.viewModel = viewModel
        return binding.root
    }

    companion object{
        private const val COURSE_ID = "COURSE_ID"
        fun createBundleWithIndex(courseId: Int): Bundle {
            return bundleOf(COURSE_ID to courseId)
        }
    }

}