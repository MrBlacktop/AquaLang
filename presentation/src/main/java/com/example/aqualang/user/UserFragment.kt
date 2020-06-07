package com.example.aqualang.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aqualang.AquaLangApplication
import com.example.aqualang.R
import com.example.aqualang.databinding.UserFragmentBinding
import javax.inject.Inject

class UserFragment : Fragment() {


    private lateinit var viewModel: UserViewModel

    @Inject
    lateinit var viewModelFactory: UserViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = UserFragmentBinding.inflate(inflater, container, false)

        (requireActivity().application as AquaLangApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this,viewModelFactory).get(UserViewModel::class.java)

        binding.viewModel = viewModel


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToLogin.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    this.findNavController().navigate(R.id.action_userFragment_to_loginActivity)
                    requireActivity().finish()
//                    viewModel.doneNavigationToLogin()
                }
            }
        })
    }
}