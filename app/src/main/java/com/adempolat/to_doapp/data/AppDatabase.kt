package com.adempolat.to_doapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adempolat.to_doapp.model.ToDoModel

@Database(entities = [ToDoModel::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun todoDao():ToDoDao
}