package com.adempolat.to_doapp.ui.newEndEdit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adempolat.to_doapp.data.Repository
import com.adempolat.to_doapp.model.Priority
import com.adempolat.to_doapp.model.ToDoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.concurrent.timerTask

@HiltViewModel
class NewAndEditViewModel @Inject constructor(application: Application,private val repository: Repository) :AndroidViewModel(application){
    val todoModel = MutableLiveData<ToDoModel>()
     fun insertToDo(title:String, description:String, isCheck:Boolean, priority: Priority){
         viewModelScope.launch {
             repository.localDataSource.insertToDo(
                 ToDoModel(
                     title=title,
                     description = description,
                     priority = priority,
                     isChecked = isCheck,
                 )
             )
         }

    }

    fun getTodo(todoId:Int){
        viewModelScope.launch {
            val toDo = repository.localDataSource.getToDo(todoId)
            todoModel.value=toDo
        }
    }

    fun updateToDo(title:String, description:String, isCheck:Boolean, priority: Priority){
        viewModelScope.launch {
            repository.localDataSource.updateToDo(ToDoModel(
                id = todoModel.value?.id ?: 0,
                title = title,
                description=description,
                isChecked = isCheck,
                priority = priority
            ))
        }
    }

    fun deletrToDo(){
        viewModelScope.launch {
            todoModel.value?.let {
                repository.localDataSource.deleteToDo(it)
            }
        }
    }
}