package com.mvvm.kanban_board.view.enter_board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mvvm.kanban_board.R

class EnterBoardFragment : Fragment() {

    companion object {
        fun newInstance() = EnterBoardFragment()
    }

    private lateinit var viewModel: EnterBoardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.enter_board_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
