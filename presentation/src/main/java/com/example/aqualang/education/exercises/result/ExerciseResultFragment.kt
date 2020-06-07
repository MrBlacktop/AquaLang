package com.example.aqualang.education.exercises.result

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aqualang.R

class ExerciseResultFragment : Fragment() {

    companion object {
        fun newInstance() = ExerciseResultFragment()
    }

    private lateinit var viewModel: ExerciseResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.exercise_result_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ExerciseResultViewModel::class.java)
        // TODO: Use the ViewModel
    }

}