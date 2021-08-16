package com.example.temp_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.temp_login.databinding.ActivityHomePageBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomePageActivity : AppCompatActivity() {

    private lateinit var homePageBinding: ActivityHomePageBinding
    private lateinit var database: UserRoomDatabase

    private val usname = intent.getStringExtra("uname")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePageBinding = DataBindingUtil.setContentView(this,R.layout.activity_home_page)

        homePageBinding.wlcmText.text = "Welcome $usname"

        database = UserRoomDatabase.getDatabase(this)

        homePageBinding.saveBtn.setOnClickListener {
            savePersonalDetails(it)
        }

    }

    private fun savePersonalDetails(it: View?) {
        val firstName=homePageBinding.fName.text.toString()
        val lastName=homePageBinding.lName.text.toString()

        //here need to call insert for personal details but its constructor
        //requires user_id
        //1-> so either get user_id from usname and pass user_id make function in dao for that
        //2-> use username as connecting link between two tables but that requires changing db
        val userId: Int? = usname?.let { it1 -> database.userDao().getUserId(it1) }

        GlobalScope.launch {
            userId?.let { it1 ->
                UserPersonalDetailsEnt(firstName,lastName,
                    it1
                )
            }?.let { it2 -> database.userDao().insertPersonalDetails(it2) }
        }

        

    }
}