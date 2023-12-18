package com.adempolat.to_doapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.adempolat.to_doapp.data.Repository
import com.adempolat.to_doapp.di.TodoApplication
import com.adempolat.to_doapp.model.Priority
import com.adempolat.to_doapp.model.ToDoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application, private val repository: Repository
):AndroidViewModel(application) {

    val toDoList = repository.localDataSource.getAllToDo().asLiveData()
    var searchTodoList: LiveData<List<ToDoModel>> = MutableLiveData()
    val searchQuery = MutableLiveData("")

    fun updateToDo(toDoModel: ToDoModel){
        val updatedToDoModel = toDoModel.copy(isChecked = toDoModel.isChecked?.not())
        viewModelScope.launch {
            repository.localDataSource.updateToDo(updatedToDoModel)
        }
    }

    fun searchTodo(searchQuery: String){
        searchTodoList = repository.localDataSource.searchTodo("%$searchQuery%").asLiveData()
        this.searchQuery.value=searchQuery
    }

}