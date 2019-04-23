package com.mvvm.kanban_board.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvvm.kanban_board.data.db.entity.User

@Dao
interface KanbanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: User)

    @Query("select * from users where idU = :id ")
    fun getUserByID(id: String): LiveData<User>


    //for testing
    @Query("select * from users where name = :name ")
    fun getUserByName(name: String): User

}