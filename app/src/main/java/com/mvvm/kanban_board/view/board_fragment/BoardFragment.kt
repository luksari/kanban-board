package com.mvvm.kanban_board.view.board_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mvvm.kanban_board.R
import com.mvvm.kanban_board.databinding.BoardFragmentBinding
import com.mvvm.kanban_board.session.AuthenticationState
import kotlinx.android.synthetic.main.board_fragment.*
import kotlinx.android.synthetic.main.task_list_item.*
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

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadUI()
    }


    private fun loadUI(){
        viewModel.loadPages(currentPageName)
        setupListUpdate()
        setupListeners()
    }

    private fun setupListUpdate(){
        viewModel.pageTasks.observe(this, Observer {
                tasks -> tasks?.let{
                    if(tasks.isNotEmpty())
                    viewModel.setAdapter(tasks) }
        })
    }

    private fun setupListeners(){
        val navController = findNavController()

        viewModel.isTaskAdded.observe(viewLifecycleOwner, Observer { isTaskAdded ->
            if(isTaskAdded) navController.navigate(R.id.cardDetailsFragment)
        })
        viewModel.selectedTask.observe(viewLifecycleOwner, Observer {
            navController.navigate(R.id.cardDetailsFragment) })
    }


}
