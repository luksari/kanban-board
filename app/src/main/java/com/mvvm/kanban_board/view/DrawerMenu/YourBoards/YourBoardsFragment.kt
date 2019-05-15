package com.mvvm.kanban_board.view.DrawerMenu.YourBoards

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mvvm.kanban_board.R

class YourBoardsFragment : Fragment() {

    companion object {
        fun newInstance() = YourBoardsFragment()
    }

    private lateinit var viewModel: YourBoardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.your_boards_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(YourBoardsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
