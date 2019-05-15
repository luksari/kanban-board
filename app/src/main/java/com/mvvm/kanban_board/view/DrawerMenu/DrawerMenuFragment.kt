package com.mvvm.kanban_board.view.DrawerMenu

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mvvm.kanban_board.R

class DrawerMenuFragment : Fragment() {

    companion object {
        fun newInstance() = DrawerMenuFragment()
    }

    private lateinit var viewModel: DrawerMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.drawer_menu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DrawerMenuViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
