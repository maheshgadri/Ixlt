package com.example.ixltech.ui.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true) val id: Int,

    val first_name: String,
    val last_name: String,
    val mobile: String,
    val gender: String,
    val dob: String,
    val emp_no: String,
    val emp_name: String,
    val designation: String,
    val account_type: String,
    val work_exp: String,
    val image: Bitmap,
    val bank_name: String,
    val branches: String,
    val account_number: String,
    val ifsc_code: String,


    )
