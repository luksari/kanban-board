package com.mvvm.kanban_board.view.top_bar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mvvm.kanban_board.R
import org.koin.android.viewmodel.ext.android.viewModel

class TopBarFragment : Fragment() {

    companion object {
        fun newInstance() = TopBarFragment()
    }

    private val viewModel: TopBarViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_bar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
