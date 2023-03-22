package com.example.ixltech.ui.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ixltech.ui.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
     fun getAllUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}