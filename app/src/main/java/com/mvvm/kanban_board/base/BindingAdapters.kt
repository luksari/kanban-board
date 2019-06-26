package com.mvvm.kanban_board.base

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("setAdapter")
fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>){
    recyclerView.hasFixedSize()
    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
}