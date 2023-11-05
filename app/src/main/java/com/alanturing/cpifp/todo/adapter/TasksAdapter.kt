package com.alanturing.cpifp.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alanturing.cpifp.todo.databinding.TodoItemBinding
import com.alanturing.cpifp.todo.model.Task

class TasksAdapter(val datos:List<Task>, var onShareClicked: (t:Task, v:View) -> Unit): RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(private val binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindTask(t:Task){
            //TODO("Asignar los elementos de pantalla")
            binding.textView.text = t.title
            binding.textView2.text = t.description
            binding.switch1.isChecked = t.isCompleted
            binding.btnShare.setOnClickListener{
                onShareClicked(t, it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false,)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return datos.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val task = datos[position]
        holder.bindTask(task)
    }
}