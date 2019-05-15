package com.mvvm.kanban_board.view.TopBar.PagesBar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mvvm.kanban_board.R

class PagesBarFragment : Fragment() {

    companion object {
        fun newInstance() = PagesBarFragment()
    }

    private lateinit var viewModel: PagesBarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pages_bar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PagesBarViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
