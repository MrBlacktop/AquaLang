package com.example.aqualang.education.glossary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aqualang.AquaLangApplication
import com.example.aqualang.R
import kotlinx.android.synthetic.main.glossary_fragment.*
import javax.inject.Inject

class GlossaryFragment : Fragment() {

    private lateinit var viewModel: GlossaryViewModel

    @Inject
    lateinit var viewModelFactory: GlossaryViewModelFactory

    @Inject
    lateinit var glossaryAdapter: GlossaryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as AquaLangApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(GlossaryViewModel::class.java)

        return inflater.inflate(R.layout.glossary_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.glossary.value?.let {
            glossaryAdapter.data = it
        }

        glossaryRecyclerView.adapter = glossaryAdapter
        glossaryRecyclerView.layoutManager = LinearLayoutManager(context)

        val swipeHandler = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val glossaryWord = glossaryAdapter.data[viewHolder.adapterPosition]
                viewModel.deleteWord(glossaryWord)
                glossaryAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(glossaryRecyclerView)

        viewModel.glossary.observe(viewLifecycleOwner, Observer {
            it?.let {
                glossaryAdapter.data = it
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

        addWordButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_glossaryFragment_to_wordFragment)
        }

    }
}