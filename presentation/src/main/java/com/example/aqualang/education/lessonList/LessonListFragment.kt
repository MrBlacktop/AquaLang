package com.example.aqualang.education.lessonList

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
import com.example.aqualang.education.lesson.LessonFragment
import com.example.domain.interactors.LessonInteractor
import kotlinx.android.synthetic.main.lesson_list_fragment.*
import javax.inject.Inject

class LessonListFragment : Fragment() {

    private lateinit var viewModel: LessonListViewModel

    @Inject
    lateinit var lessonInteractor: LessonInteractor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity().application as AquaLangApplication).appComponent.inject(this)

        val topicId = requireArguments().getInt(TOPIC_ID)

        val viewModelFactory = LessonListViewModelFactory(lessonInteractor, topicId)

        viewModel = ViewModelProvider(this, viewModelFactory).get(LessonListViewModel::class.java)

        return inflater.inflate(R.layout.lesson_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LessonAdapter(LessonAdapter.LessonListener {
            this.findNavController().navigate(
                R.id.action_lessonListFragment_to_lessonFragment,
                LessonFragment.createBundleWithIndex(it)
            )
        })

        viewModel.lessons.value?.let {
            adapter.data = it
        }

        lessonList.layoutManager = LinearLayoutManager(context)
        lessonList.adapter = adapter

        viewModel.showNetworkErrorToast.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show()
                    viewModel.doneShowingToast()
                }
            }
        })

        viewModel.lessons.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })
    }

    companion object {
        private const val TOPIC_ID = "TOPIC_ID"

        fun createBundleWithIndex(topicId: Int): Bundle {
            return bundleOf(TOPIC_ID to topicId)
        }
    }
}