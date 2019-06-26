package com.mvvm.kanban_board.view.top_bar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mvvm.kanban_board.view.board.BoardFragment

class PagesPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){


    //override fun getItem(position: Int): Fragment = BoardFragment()


    override fun getItem(position: Int): Fragment {

        val args = Bundle()
        val name = getPageTitle(position).toString()
        val instance =  BoardFragment()
        instance.currentPageName = name
        Log.d("BOARD AD", name)
        return instance
    }
    override fun getCount(): Int = 4

    override fun getPageTitle(position: Int): CharSequence? = when(position){
        0->"To do"
        1->"In Progress"
        2->"Testing"
        3->"Done"
        else -> {
            "PUTA QUE PARIU?!!?!?!"
        }
    }
}



