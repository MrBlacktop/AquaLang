package com.example.aqualang.login

import com.example.aqualang.login.login.LoginFragment
import com.example.aqualang.login.registration.RegistrationFragment
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent
interface LoginComponent{
    @Subcomponent.Factory
    interface Factory{
        fun create(): LoginComponent
    }

    fun inject(loginFragment: LoginFragment)
    fun inject(registrationFragment: RegistrationFragment)
}