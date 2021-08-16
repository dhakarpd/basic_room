package com.example.temp_login

import androidx.room.*

@Entity(tableName = "login_data",
    indices = [Index(value = ["user_name"], unique = true)])
class UserDataEnt(
    @ColumnInfo(name = "user_name")val userName:String,
    @ColumnInfo(name = "password")val password: String) {

    @PrimaryKey(autoGenerate = true) var id:Int = 0
}


@Entity(tableName = "personal_details",
        foreignKeys = arrayOf(ForeignKey(entity = UserDataEnt::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE)
))
class UserPersonalDetailsEnt(
    @ColumnInfo(name = "first_name")val firstName:String,
    @ColumnInfo(name = "last_name")val lastName:String,
    @ColumnInfo(name = "user_id")val id: Int){

    @PrimaryKey(autoGenerate = true) var details_id:Int = 0
}