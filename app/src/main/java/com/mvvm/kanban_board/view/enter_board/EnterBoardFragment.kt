package com.mvvm.kanban_board.view.enter_board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.mvvm.kanban_board.R
import com.mvvm.kanban_board.databinding.CreateBoardFragmentBinding
import com.mvvm.kanban_board.databinding.EnterBoardFragmentBinding
import com.mvvm.kanban_board.view.sign_up.SignUpViewModel
import kotlinx.android.synthetic.main.enter_board_fragment.*
import kotlinx.android.synthetic.main.sign_up_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class EnterBoardFragment : Fragment() {

    companion object {
        fun newInstance() = EnterBoardFragment()
    }

    private val viewModel:  EnterBoardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : EnterBoardFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.enter_board_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
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
