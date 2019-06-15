package com.mvvm.kanban_board.view.sign_in

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
import com.mvvm.kanban_board.databinding.SignInFragmentBinding
import com.mvvm.kanban_board.session.AuthenticationState
import kotlinx.android.synthetic.main.sign_in_fragment.*
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
    private fun setupListeners(){
        val navController = findNavController()
        go_to_sign_up.setOnClickListener {
            navController.navigate(R.id.signUpFragment)
        }
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            // If user is authenticated succesfully, navigate to the board fragment
            if(authenticationState ==  AuthenticationState.AUTHENTICATED)
                navController.navigate(R.id.boardFragment)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupListeners()
        super.onActivityCreated(savedInstanceState)
    }

}
