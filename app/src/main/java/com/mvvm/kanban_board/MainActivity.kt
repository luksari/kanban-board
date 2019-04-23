package com.mvvm.kanban_board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvvm.kanban_board.data.Repo.Repository
import org.koin.android.ext.android.inject

//import org.kodein.di.KodeinAware
//import org.kodein.di.android.closestKodein
//import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity(){//}, KodeinAware {

    //override val kodein by closestKodein()
    //private val myRepo: Repository  by instance()
    val myRepo: Repository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        myRepo.test()
//        val myRepo: Repository = get()
       // myRepo.test()


    }
}
