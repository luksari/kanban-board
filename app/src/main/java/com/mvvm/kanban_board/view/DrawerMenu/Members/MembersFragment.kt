package com.mvvm.kanban_board.view.DrawerMenu.Members

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mvvm.kanban_board.R
import org.koin.android.viewmodel.ext.android.viewModel

class MembersFragment : Fragment() {

    companion object {
        fun newInstance() = MembersFragment()
    }

    private val viewModel: MembersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.members_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
