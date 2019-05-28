package com.mvvm.kanban_board.view.sign_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.mvvm.kanban_board.R
import com.mvvm.kanban_board.databinding.SignUpFragmentBinding
import kotlinx.android.synthetic.main.sign_up_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private val viewModel: SignUpViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : SignUpFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.sign_up_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
    private fun setupListeners(){
        go_to_sign_in.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupListeners()
        super.onActivityCreated(savedInstanceState)
    }

}
