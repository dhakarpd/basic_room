package com.example.temp_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.temp_login.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var regBinding:ActivityMainBinding
    //lateinit var viewModel: UserViewModel
    private lateinit var database: UserRoomDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        regBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        regBinding.submitBtn.setOnClickListener {
            onSubmitClick(it)
        }

        regBinding.goToSignInBtn.setOnClickListener {
            onGoToSignInClick(it)
        }



        /*viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(UserViewModel::class.java)


        viewModel.allUsers.observe(this, Observer {
            
        })*/

        database = UserRoomDatabase.getDatabase(this)


    }



    private fun onSubmitClick(it: View?) {
        /*database.userDao().getAllUsersData().observe(this, Observer {
            Log.d("Hello Brother",it.toString())
        })*/

        val uname:String = regBinding.userName.text.toString()
        val pwd:String = regBinding.passWord.text.toString()

        //Toast.makeText(this,"Hello $uname ur password is $pwd",Toast.LENGTH_SHORT).show()
        GlobalScope.launch {
            database.userDao().insertUser(UserDataEnt(uname,pwd))
        }
        val intent_reg = Intent(this,HomePageActivity::class.java)
        intent_reg.putExtra("uname",uname)
        startActivity(intent_reg)
        finish()
    }

    private fun onGoToSignInClick(it: View?) {
        val intent = Intent(this,SignInActivity::class.java)
        startActivity(intent)
    }
}