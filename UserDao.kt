package com.example.temp_login

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userDataEnt: UserDataEnt)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPersonalDetails(userPersonalDetailsEnt: UserPersonalDetailsEnt)

    @Query("select * from login_data")
    fun getAllUsersData():LiveData<List<UserDataEnt>>

    @Query("select * from login_data where user_name =:uname and password=:pwd")
    fun authenticateUser(uname: String, pwd: String): LiveData<List<UserDataEnt>>

    @Query("SELECT * FROM login_data inner join personal_details on login_data.id=personal_details.user_id")
    fun getAllUsersDataAndPersonalDetails():LiveData<List<UserAndPersonalDetails>>

    @Query("select id from login_data where user_name =:uname")
    fun getUserId(uname: String): Int

}