package com.mvvm.kanban_board.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.google.android.material.navigation.NavigationView
import com.mvvm.kanban_board.R
import com.mvvm.kanban_board.view.Board.ActivitiesAdapter
import com.mvvm.kanban_board.view.TopBar.PagesBar.PagesAdapter
import kotlinx.android.synthetic.main.action_bar_fragment.*
import kotlinx.android.synthetic.main.activities_list_fragment.*
import kotlinx.android.synthetic.main.drawer_menu_fragment.*
import kotlinx.android.synthetic.main.pages_bar_fragment.*
import kotlinx.android.synthetic.main.top_bar_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() { //, NavigationView.OnNavigationItemSelectedListener

    private val viewModel : MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
//        initActionBar()



        //temporary loading from json here with context
       // myRepo.initializeFakeData(this.applicationContext)

        //pagesList
        val pages: ArrayList<String> = ArrayList()

        for(i in 1..10) {
            pages.add("Page $i")
        }

        pagesList.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)
        pagesList.adapter = PagesAdapter(pages)

        //activitiesList
        val activities: ArrayList<String> = ArrayList()

        for(i in 1..10) {
            activities.add("Do $i")
        }

        activitiesList.layoutManager = LinearLayoutManager(this)
        activitiesList.adapter = ActivitiesAdapter(activities)

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
}
