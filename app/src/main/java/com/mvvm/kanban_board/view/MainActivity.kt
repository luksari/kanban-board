package com.mvvm.kanban_board.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvvm.kanban_board.R
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(){

    private val viewModel : MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //temporary loading from json here with context
       // myRepo.initializeFakeData(this.applicationContext)

    }


}
