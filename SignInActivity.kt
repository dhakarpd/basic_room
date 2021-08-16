package com.example.temp_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.temp_login.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    lateinit var signInBinding:ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInBinding=DataBindingUtil.setContentView(this,R.layout.activity_sign_in)

        signInBinding.signInBtn.setOnClickListener {
            onSignInClick(it)
        }
    }

    private fun onSignInClick(it: View?) {
        val intent = Intent(this,HomePageActivity::class.java)
        startActivity(intent)
        finish()
    }
}