package com.adempolat.to_doapp.data

import com.adempolat.to_doapp.model.ToDoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val todoDao:ToDoDao) {
    fun getAllToDo(): Flow<List<ToDoModel>>{
        return todoDao.getAllToDo()
    }

    fun searchTodo(searchQuery:String): Flow<List<ToDoModel>> {
        return todoDao.searchTodo(searchQuery)
    }

    suspend fun insertToDo(toDoModel: ToDoModel) {
        todoDao.insertToDo(toDoModel)
    }

    suspend fun getToDo(toDoId: Int): ToDoModel {
        return todoDao.getToDo(toDoId)
    }

    suspend fun updateToDo(toDoModel: ToDoModel) {
        todoDao.updateToDo(toDoModel)
    }

    suspend fun deleteToDo(toDoModel: ToDoModel) {
        todoDao.deleteToDo(toDoModel)
    }
}