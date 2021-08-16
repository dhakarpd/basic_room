package com.example.temp_login

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val allUsers:LiveData<List<UserDataEnt>> = userDao.getAllUsersData()

    suspend fun insert(userDataEnt: UserDataEnt){
        userDao.insert(userDataEnt)
    }

    fun userAuthentication(username: String, password: String): LiveData<UserDataEnt> {
        return userDao.authenticateUser(username, password)
    }

}