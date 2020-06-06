package com.example.aqualang.education.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aqualang.AquaLangApplication
import com.example.aqualang.R
import com.example.aqualang.databinding.CourseFragmentBinding
import com.example.aqualang.education.lessonList.LessonListFragment
import com.example.domain.interactors.CourseInteractor
import com.example.domain.interactors.TopicInteractor
import javax.inject.Inject

class CourseFragment : Fragment() {

    private lateinit var binding: CourseFragmentBinding

    private lateinit var viewModel: CourseViewModel

    @Inject
    lateinit var courseInteractor: CourseInteractor

    @Inject
    lateinit var topicInteractor: TopicInteractor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CourseFragmentBinding.inflate(inflater, container, false)
        (requireActivity().application as AquaLangApplication).appComponent.inject(this)

        val id = requireArguments().getInt(COURSE_ID)

        val viewModelFactory = CourseViewModelFactory(courseInteractor, topicInteractor, id)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CourseViewModel::class.java)

        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showNetworkErrorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show()
                    viewModel.doneShowingToast()
                }
            }
        })

        val adapter = TopicAdapter(
            viewModel.course,
            TopicAdapter.TopicListener {
                this.findNavController().navigate(
                    R.id.action_courseFragment_to_lessonListFragment,
                    LessonListFragment.createBundleWithIndex(it)
                )
            })

        viewModel.topics.value?.let {
            adapter.data = it
        }

        binding.topicList.adapter = adapter
        binding.topicList.layoutManager = LinearLayoutManager(context)

        viewModel.topics.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        viewModel.courseIsActive.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.notifyDataSetChanged()
                if (it) {
                    binding.subscribeButton.visibility = View.GONE
                    binding.unsubscribeButton.visibility = View.VISIBLE
                } else {
                    binding.subscribeButton.visibility = View.VISIBLE
                    binding.unsubscribeButton.visibility = View.GONE
                }
            }
        })
    }

    companion object {
        private const val COURSE_ID = "COURSE_ID"
        fun createBundleWithIndex(courseId: Int): Bundle {
            return bundleOf(COURSE_ID to courseId)
        }
    }

}