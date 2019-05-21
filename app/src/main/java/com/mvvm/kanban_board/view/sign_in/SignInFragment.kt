package com.mvvm.kanban_board.view.sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.mvvm.kanban_board.R
import com.mvvm.kanban_board.databinding.SignInFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private val viewModel: SignInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : SignInFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.sign_in_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
