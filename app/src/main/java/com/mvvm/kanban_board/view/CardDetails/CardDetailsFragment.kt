package com.mvvm.kanban_board.view.CardDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.mvvm.kanban_board.R

class CardDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = CardDetailsFragment()
    }

    private lateinit var viewModel: CardDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.card_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CardDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
