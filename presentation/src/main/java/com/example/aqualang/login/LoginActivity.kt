package com.example.aqualang.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aqualang.AquaLangApplication
import com.example.aqualang.R

class LoginActivity : AppCompatActivity() {
    lateinit var loginComponent: LoginComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        loginComponent = (application as AquaLangApplication)
            .appComponent
            .loginComponent().create()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}