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

        //temporary loading from json here with context
        myRepo.initializeFakeData(this.applicationContext)

    }
}
