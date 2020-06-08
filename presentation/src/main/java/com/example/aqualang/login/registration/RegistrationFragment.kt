package com.example.aqualang.login.registration

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aqualang.AquaLangApplication
import com.example.aqualang.R
import com.example.aqualang.databinding.RegistrationFragmentBinding
import com.example.aqualang.login.LoginActivity
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: RegistrationViewModelFactory

    private lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = RegistrationFragmentBinding.inflate(inflater, container, false)

        (requireActivity() as LoginActivity).loginComponent.inject(this)

        viewModel = ViewModelProvider(this,viewModelFactory).get(RegistrationViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToLogin.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    this.findNavController()
                        .navigate(R.id.action_registrationFragment2_to_loginFragment2)
                    viewModel.doneNavigationToLogin()
                }
            }
        })

        viewModel.showToast.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    Toast.makeText(context, viewModel.toastText, Toast.LENGTH_SHORT).show()
                    viewModel.doneShowingToast()
                }
            }
        })
    }
}