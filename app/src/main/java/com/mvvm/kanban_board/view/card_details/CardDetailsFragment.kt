package com.mvvm.kanban_board.view.card_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.mvvm.kanban_board.R
import com.mvvm.kanban_board.databinding.CardDetailsFragmentBinding
import com.mvvm.kanban_board.databinding.EnterBoardFragmentBinding
import com.mvvm.kanban_board.view.enter_board.EnterBoardFragment
import com.mvvm.kanban_board.view.enter_board.EnterBoardViewModel
import kotlinx.android.synthetic.main.enter_board_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class CardDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = CardDetailsFragment()
    }

    private val viewModel: CardDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : CardDetailsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.card_details_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


}
