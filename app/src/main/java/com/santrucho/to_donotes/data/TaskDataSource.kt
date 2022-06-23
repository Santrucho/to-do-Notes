package com.santrucho.to_donotes.data

import androidx.lifecycle.LiveData

interface TaskDataSource {

    fun observeTasks() : LiveData<Result<List<Task>>>

    suspend fun getTasks() : Result<List<Task>>

    suspend fun refreshTasks()

    fun observeTask(taskId : String) : LiveData<Result<Task>>

    suspend fun getTask(taskId: String) : Result<Task>

    suspend fun refreshTask(taskId:String)

    suspend fun saveTask(task:Task)

    suspend fun completeTask(task : Task)

    suspend fun completeTask(taskId: String)

    suspend fun activeTask(task:Task)

    suspend fun activeTask(taskId: String)

    suspend fun clearCompletedTask()

    suspend fun deleteAllTask()

    suspend fun deleteTask(taskId: String)
}