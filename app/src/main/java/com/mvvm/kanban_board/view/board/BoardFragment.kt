package com.mvvm.kanban_board.view.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.mvvm.kanban_board.R
import com.mvvm.kanban_board.databinding.BoardFragmentBinding
import com.mvvm.kanban_board.databinding.SignInFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class BoardFragment : Fragment() {

    var currentPageName: String = ""

    companion object {
        fun newInstance() = BoardFragment()
    }

    private val viewModel: BoardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : BoardFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.board_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        loadUI()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun loadUI(){
        viewModel.loadPages(currentPageName)
    }



}
