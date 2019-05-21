package com.mvvm.kanban_board.view.drawer_menu.your_boards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mvvm.kanban_board.R
import org.koin.android.viewmodel.ext.android.viewModel

class YourBoardsFragment : Fragment() {

    companion object {
        fun newInstance() = YourBoardsFragment()
    }

    private val viewModel: YourBoardsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.your_boards_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
