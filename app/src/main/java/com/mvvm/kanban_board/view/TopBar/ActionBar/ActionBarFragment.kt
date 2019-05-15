package com.mvvm.kanban_board.view.TopBar.ActionBar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mvvm.kanban_board.R

class ActionBarFragment : Fragment() {

    companion object {
        fun newInstance() = ActionBarFragment()
    }

    private lateinit var viewModel: ActionBarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.action_bar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ActionBarViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
