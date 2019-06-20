package com.mvvm.kanban_board.view.enter_board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.mvvm.kanban_board.R
import kotlinx.android.synthetic.main.enter_board_fragment.*
import kotlinx.android.synthetic.main.sign_up_fragment.*

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

    private fun setupListeners(){
        go_to_create_board.setOnClickListener {
            findNavController().navigate(R.id.createBoardFragment)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupListeners()
        super.onActivityCreated(savedInstanceState)
    }

}
