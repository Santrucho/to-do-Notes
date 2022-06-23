package com.santrucho.to_donotes.data.source.local

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.santrucho.to_donotes.data.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase()  {
    abstract fun taskDao() : TaskDao
}