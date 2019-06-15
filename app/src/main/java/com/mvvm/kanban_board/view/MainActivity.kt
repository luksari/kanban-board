package com.mvvm.kanban_board.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mvvm.kanban_board.R
import com.mvvm.kanban_board.view.Board.ActivitiesAdapter
import com.mvvm.kanban_board.view.TopBar.PagesBar.PagesAdapter
import kotlinx.android.synthetic.main.action_bar_fragment.*
import kotlinx.android.synthetic.main.activities_list_fragment.*
import kotlinx.android.synthetic.main.pages_bar_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() { //, NavigationView.OnNavigationItemSelectedListener

    private val viewModel : MainActivityViewModel by viewModel()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(toolbar)
//        val pages: ArrayList<String> = ArrayList()
//
//        for(i in 1..10) {
//            pages.add("Page $i")
//        }
//
//        pagesList.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)
//        pagesList.adapter = PagesAdapter(pages)
//
//        val activities: ArrayList<String> = ArrayList()
//
//        for(i in 1..10) {
//            activities.add("Do $i")
//        }
//
//        activitiesList.layoutManager = LinearLayoutManager(this)
//        activitiesList.adapter = ActivitiesAdapter(activities)

    }
}

//    //drawerMenu
//    private fun initActionBar() {
//        val toogle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.nav_open, R.string.nav_close) <-- coś tu nie działa
//        drawer_layout.addDrawerListener(toogle)
//        toogle.syncState()
//        navigation_view.setNavigationItemSelectedListener(this)
//    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.nav_members -> {
//
//            }
//
//            R.id.nav_activities -> {
//
//            }
//
//            R.id.nav_your_boards -> {
//
//            }
//
//            R.id.nav_settings -> {
//
//            }
//
//            else -> {
//
//            }
//        }
//        drawer_layout.closeDrawer(GravityCompat.START)
//        return true
//    }

