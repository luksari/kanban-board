package com.mvvm.kanban_board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvvm.kanban_board.data.Repo.Repository
import org.koin.android.ext.android.inject



class MainActivity : AppCompatActivity(){

    val myRepo: Repository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//only for checking injection
        myRepo.checkRepoInjection()

    }
}
