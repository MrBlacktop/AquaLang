package com.example.aqualang.education.coursesList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aqualang.AquaLangApplication
import com.example.aqualang.R
import kotlinx.android.synthetic.main.courses_list_fragment.*
import javax.inject.Inject

class CoursesListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: CoursesListViewModelFactory

    private lateinit var viewModel: CoursesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity().application as AquaLangApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CoursesListViewModel::class.java)

        return inflater.inflate(R.layout.courses_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CourseAdapter(
            CourseAdapter.CourseListener{
                this.findNavController()
            }
        )

        coursesRecyclerView.adapter = adapter
        coursesRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.courses.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        viewModel.navigateToLogin.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    this.findNavController()
                        .navigate(R.id.action_coursesListFragment_to_loginFragment)
                    viewModel.doneNavigatingToLogin()
                }
            }
        })
    }
}