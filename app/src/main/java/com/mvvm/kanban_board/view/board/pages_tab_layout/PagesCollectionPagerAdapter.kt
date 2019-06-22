package com.mvvm.kanban_board.view.board.pages_tab_layout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mvvm.kanban_board.view.board.BoardFragment

class PagesCollectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment = BoardFragment()

    override fun getCount(): Int = 4

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            1 -> "Todo"
            2 -> "In progress"
            3 -> "Testing"
            4 -> "Done"
            else -> {
                "PUTA QUE PARIU?!!?!?"
            }
        }
    }



}