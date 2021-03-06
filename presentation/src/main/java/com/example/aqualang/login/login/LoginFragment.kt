package com.example.aqualang.login.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aqualang.AquaLangApplication
import com.example.aqualang.R
import com.example.aqualang.databinding.LoginFragmentBinding
import com.example.aqualang.login.LoginActivity
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LoginFragmentBinding.inflate(inflater,container,false)

        (requireActivity() as LoginActivity).loginComponent.inject(this)


        viewModel = ViewModelProvider(this,viewModelFactory).get(LoginViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToCoursesList.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    this.findNavController()
                        .navigate(R.id.action_loginFragment2_to_mainActivity)
                    requireActivity().finish()

                }
            }
        })

        viewModel.navigateToRegistration.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    this.findNavController()
                        .navigate(R.id.action_loginFragment2_to_registrationFragment2)
                    viewModel.doneNavigatingToRegistration()
                }
            }
        })

        viewModel.showLoginErrorToast.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    Toast.makeText(context, "Login error", Toast.LENGTH_LONG).show()
                    viewModel.doneShowingErrorToast()
                }

            }
        })
    }
}