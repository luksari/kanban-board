package com.mvvm.kanban_board.view.create_board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.mvvm.kanban_board.R
import kotlinx.android.synthetic.main.create_board_fragment.*
import kotlinx.android.synthetic.main.enter_board_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class CreateBoardFragment : Fragment() {

    companion object {
        fun newInstance() = CreateBoardFragment()
    }

    private val viewModel: CreateBoardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_board_fragment, container, false)
    }

    private fun setupListeners(){
        go_to_enter_board.setOnClickListener {
            findNavController().navigate(R.id.enterBoardFragment)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
