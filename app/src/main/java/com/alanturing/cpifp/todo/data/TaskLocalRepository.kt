package com.alanturing.cpifp.todo.data

import com.alanturing.cpifp.todo.model.Task

class TaskLocalRepository() {
    companion object {
        private var _INSTANCE:TaskLocalRepository? = null
        fun getInstance():TaskLocalRepository {
            if(_INSTANCE == null){
                _INSTANCE = TaskLocalRepository()
            }
            return _INSTANCE!!
        }
    }
    private val _tasks = mutableListOf<Task>()
    /*init {

        //TODO("Borrar cuando el repositorio este listo")
        _tasks.add(Task(1,"Comprar leche","Leche desnatada",false))
        _tasks.add(Task(2,"Hacer práctica Android","Completar todos los TODOS",false))

    }*/

    private var contadorId:Int = 0

    val tasks:List<Task>
        get() = _tasks

    fun add(task:Task) {
        //TODO("Código crear tarea")
        _tasks.add(task)
    }
    fun delete(id:Int) {
        //TODO("Código eliminar tarea por id")
        val taskToRemove = _tasks.find { it.id == id }
        taskToRemove?.let {
            _tasks.remove(it)
        }
    }
    fun update(task:Task) {
        //TODO("Código actualizar tarea con id==id")
        val index = _tasks.indexOfFirst { it.id== task.id }
        if (index != -1){
            _tasks[index] = task
        }
    }

    fun getNextTaskId():Int{
        return ++contadorId
    }
}