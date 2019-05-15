package com.mvvm.kanban_board.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvvm.kanban_board.data.db.entity.Board
import com.mvvm.kanban_board.data.db.entity.User

@Dao
interface KanbanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertUser(user: User)

    @Query("select * from users where idU = :id ")
    fun getUserByID(id: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertBoard(board: Board)

    @Query("select * from boards where idB = :id ")
    fun getBoardByID(id: String): LiveData<Board>



    //for testing without LiveData
    @Query("select * from users where name = :name ")
    fun getUserByName(name: String): User

    @Query("select * from users")
    fun getUsers(): Array<User>

    @Query("select * from boards where name = :name ")
    fun getBoardByName(name: String): Board

}