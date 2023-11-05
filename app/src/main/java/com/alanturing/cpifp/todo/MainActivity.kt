package com.alanturing.cpifp.todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.alanturing.cpifp.todo.adapter.TasksAdapter
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding
import com.alanturing.cpifp.todo.model.Task
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val repositorio = TaskLocalRepository.getInstance()
    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                Activity.RESULT_OK ->{
                    binding.tasks.adapter = TasksAdapter(repositorio.tasks, ::onShareItem, ::onEditCard)
                }
                Activity.RESULT_CANCELED ->{
                    Snackbar.make(this,binding.root,"Se ha cancleado",Snackbar.LENGTH_SHORT).show()
                }
            }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tasks.adapter = TasksAdapter(repositorio.tasks, ::onShareItem, ::onEditCard)

        binding.extendedFab.setOnClickListener {
            val intent = Intent(this, CreateToDoActivity::class.java)
            // Esto se quita  - -- - - - - -- - - - - - startActivity(intent)
            getResult.launch(intent)
        }
    }

    fun onShareItem(task: Task, view: View){
        val statusItem = if(task.isCompleted) "Completada"
                        else "pendiente"
        val shareText = getString(R.string.share_text,task.title, task.description,statusItem)
        val intent = Intent().apply{
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,"COMPARTIR")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    fun onEditCard(task:Task, view:View){
        val editIntent = Intent(this, EditToDoActivity:: class.java)
        editIntent.putExtra("TASK", task)
        startActivity(editIntent)
    }

    override fun onResume(){
        super.onResume()
        //binding.tasks.adapter = TasksAdapter(TaskLocalRepository.getInstance().tasks)
    }
}