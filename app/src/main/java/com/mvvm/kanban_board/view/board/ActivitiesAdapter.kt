package com.mvvm.kanban_board.view.Board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.kanban_board.R

class ActivitiesAdapter(val activities: ArrayList<String>) : RecyclerView.Adapter<ActivitiesAdapter.ViewHolder>() {

    override fun getItemCount() = activities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.activity.text = activities[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activities_edit_text, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activity: TextView = itemView.findViewById(R.id.activity1)
    }
}