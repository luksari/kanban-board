package com.mvvm.kanban_board.view.board_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.kanban_board.R
import com.mvvm.kanban_board.data.apiService.response.TaskResponse
import com.mvvm.kanban_board.databinding.TaskListItemBinding

class TaskListAdapter(@LayoutRes private val layoutId: Int, private val boardViewModel : BoardViewModel) :
    RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {
    private var cards : List<TaskResponse>? = boardViewModel.pageTasks.value

    fun setCards(cards: List<TaskResponse>) {
        this.cards = cards
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding : TaskListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.task_list_item, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cards?.size ?: 0
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(boardViewModel, position)
    }

    inner class TaskViewHolder(private val binding : TaskListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(viewModel: BoardViewModel, position: Int){
            binding.viewModel = viewModel
            binding.position = position
            binding.executePendingBindings()
        }
    }
}