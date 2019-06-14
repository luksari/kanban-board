package com.mvvm.kanban_board.view.TopBar.PagesBar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.kanban_board.R

class PagesAdapter(val pages: ArrayList<String>) : RecyclerView.Adapter<PagesAdapter.ViewHolder>() {

    override fun getItemCount() = pages.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.firstName.text = pages[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.pages_list_items, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstName: TextView = itemView.findViewById(R.id.firstName)
    }
}