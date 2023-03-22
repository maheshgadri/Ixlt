package com.example.ixltech.ui.db

import androidx.lifecycle.LiveData
import com.example.ixltech.ui.model.User

class MyRepository(private val userDao: UserDao) {

    val getAllUser: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun insertPerson(user: User){
        userDao.insertUser(user)
    }

}