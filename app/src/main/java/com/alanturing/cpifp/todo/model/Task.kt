package com.alanturing.cpifp.todo.model

data class Task( var id:Int,
                 var title:String,
                 var description:String,
                 var isCompleted: Boolean){
}