package com.mvvm.kanban_board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.mvvm.kanban_board.data.Repo.Repository
import com.mvvm.kanban_board.data.db.entity.Board
import com.mvvm.kanban_board.data.db.entity.BoardsResponse
import com.mvvm.kanban_board.data.db.entity.User
import org.koin.android.ext.android.inject
import java.io.InputStream


class MainActivity : AppCompatActivity(){

    val myRepo: Repository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRepo.initializeFakeData(this.applicationContext)


//
//        try {
//            val inputStreamBoards: InputStream = assets.open("data_boards.json")
//            val inputStringBoards = inputStreamBoards.bufferedReader().use{it.readText()}
//            var board = Gson().fromJson(inputStringBoards, BoardsResponse::class.java)
//            for (b in board.boards!!) {
//                Log.d("BAZA", b?.name)
//            }
//        } catch (e:Exception){
//            Log.d("BAZA",e.message)
//        }
    }
}
