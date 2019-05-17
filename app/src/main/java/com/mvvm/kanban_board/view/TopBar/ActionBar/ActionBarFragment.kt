package com.mvvm.kanban_board.view.TopBar.ActionBar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvvm.kanban_board.R
import org.koin.android.viewmodel.ext.android.viewModel

class ActionBarFragment : Fragment() {

    companion object {
        fun newInstance() = ActionBarFragment()
    }

    private val viewModel: ActionBarViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.action_bar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
