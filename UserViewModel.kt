package com.example.temp_login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val allUsers: LiveData<List<UserDataEnt>>

    init {
        val dao = UserRoomDatabase.getDatabase(application).userDao()
        val repository = UserRepository(dao)
        allUsers = repository.allUsers
    }
}