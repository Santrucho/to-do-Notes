package com.santrucho.to_donotes.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.santrucho.to_donotes.data.Result
import com.santrucho.to_donotes.data.Task

interface TaskDao {

    @Query(" SELECT * FROM tasks ")
    fun observeTasks() : LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE entryId = :taskId")
    fun observeTaskById(taskId: String) : LiveData<Task>

    @Query("SELECT * FROM tasks")
    suspend fun getTask() :List<Task>

    @Query("SELECT * FROM Tasks WHERE entryId = :taskId")
    suspend fun getTaskById(taskId: String) : Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : Task)

    @Update
    suspend fun updateTask(task: Task) : Int

    @Query("UPDATE TASKS SET completed = :completed WHERE entryId = :taskId")
    suspend fun updateCompleted(taskId: String, completed : Boolean)

    @Query("DELETE FROM Tasks WHERE entryId = :taskId")
    suspend fun deleteTaskById(taskId:String)

    @Query("DELETE FROM Tasks")
    suspend fun deleteTasks()

    @Query("DELETE FROM Tasks WHERE completed = 1")
    suspend fun deleteCompletedTask() : Int


}