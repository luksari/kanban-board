package com.mvvm.kanban_board.view.DrawerMenu.BoardHistory

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mvvm.kanban_board.R

class BoardHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = BoardHistoryFragment()
    }

    private lateinit var viewModel: BoardHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.board_history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BoardHistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
