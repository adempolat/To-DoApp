package com.adempolat.to_doapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.adempolat.to_doapp.model.ToDoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Query("SELECT * from to_do_table")
    fun getAllToDo(): Flow<List<ToDoModel>>

    @Query("SELECT * from to_do_table WHERE title LIKE :searchQuery OR description LIKE:searchQuery")
    fun searchTodo(searchQuery: String):Flow<List<ToDoModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDoModel: ToDoModel)

    @Update
    suspend fun updateToDo(toDoModel: ToDoModel)

    @Query("SELECT * from to_do_table WHERE id=:todoId")
    suspend fun getToDo(todoId:Int):ToDoModel

    @Delete
    suspend fun deleteToDo(toDoModel: ToDoModel)
}