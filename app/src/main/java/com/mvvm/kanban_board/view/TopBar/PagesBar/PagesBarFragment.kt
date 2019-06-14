package com.mvvm.kanban_board.view.TopBar.PagesBar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.mvvm.kanban_board.R
import com.mvvm.kanban_board.view.MainActivity
import kotlinx.android.synthetic.main.pages_bar_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class PagesBarFragment : Fragment() {

    companion object {
        fun newInstance() = PagesBarFragment()
    }

    private val viewModel: PagesBarViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pages_bar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
