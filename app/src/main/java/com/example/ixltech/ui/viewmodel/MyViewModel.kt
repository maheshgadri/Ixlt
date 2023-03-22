package com.example.ixltech.ui.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ixltech.ui.db.MyAppDatabase
import com.example.ixltech.ui.db.MyRepository
import com.example.ixltech.ui.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyViewModel(application: Application) : AndroidViewModel(application) {

//    private val userDao = MyAppDatabase.getDatabase(application).userDao()

    val getAllUser: LiveData<List<User>>

    val repository : MyRepository

    // on below line we are initializing
    // our dao, repository and all notes
    init {
        val dao = MyAppDatabase.getDatabase(application).userDao()
        repository = MyRepository(dao)
        getAllUser = repository.getAllUser
    }

    fun insertUser(user: User)=viewModelScope.launch(Dispatchers.IO) {
        repository.insertPerson(user)
    }
//    fun insertUser(
//        first_name:String,
//        last_name:String,
//        mobile:String,
//        gender:String,
//        dob:String,
//        emp_no:String,
//        emp_name:String,
//        designation:String,
//        account_type:String,
//        work_exp:String,
////        image:Bitmap,
//        bank_name:String,
//        branches:String,
//        account_number:String,
//        ifsc_code:String
//
//
//    ) {
//        val user = User(0, first_name, last_name, mobile,gender,dob,emp_no,emp_name,designation,
//            account_type,work_exp,bank_name,branches,account_number,ifsc_code)
//        viewModelScope.launch {
//            userDao.insertUser(user)
//        }
//    }
}