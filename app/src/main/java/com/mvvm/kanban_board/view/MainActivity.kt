package com.mvvm.kanban_board.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mvvm.kanban_board.R
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() { //, NavigationView.OnNavigationItemSelectedListener

    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }
}

