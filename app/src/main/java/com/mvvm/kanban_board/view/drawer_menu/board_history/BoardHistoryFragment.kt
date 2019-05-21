package com.mvvm.kanban_board.view.drawer_menu.board_history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mvvm.kanban_board.R
import org.koin.android.viewmodel.ext.android.viewModel

class BoardHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = BoardHistoryFragment()
    }

    private val viewModel: BoardHistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.board_history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
