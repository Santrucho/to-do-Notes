package com.santrucho.to_donotes.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.santrucho.to_donotes.data.Result
import com.santrucho.to_donotes.data.Result.Success
import com.santrucho.to_donotes.data.Result.Failure
import com.santrucho.to_donotes.data.Task
import com.santrucho.to_donotes.data.TaskDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.withContext
import java.lang.Exception


class TaskLocalDataSource internal constructor(
    private val taskDao : TaskDao,
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO
) : TaskDataSource{

    override fun observeTasks() : LiveData<Result<List<Task>>> {
        return taskDao.observeTasks().map{
            Success(it)
        }
    }

    override fun observeTask(taskId : String): LiveData<Result<Task>> {
        return taskDao.observeTaskById(taskId).map{
            Success(it)
        }
    }
    override suspend fun getTasks() : Result<List<Task>> = withContext(ioDispatcher){
        return@withContext try{
            Success(taskDao.getTask())
        } catch(e:Exception){
            Failure(e)
        }
    }

    override suspend fun refreshTask(taskId:String){
    }
    override suspend fun refreshTasks(){
    }
    override suspend fun getTask(taskId: String) : Result<Task> = withContext(ioDispatcher){
        try{
            val task = taskDao.getTaskById(taskId)
            if(task != null){
                return@withContext Success(task)
            }else{
                return@withContext Failure(Exception("Task not found"))
            }
        } catch(e : Exception){
            return@withContext Failure(e)
        }
    }

    override suspend fun saveTask(task:Task) = withContext(ioDispatcher){
        taskDao.insertTask(task)
    }

    override suspend fun completeTask(task : Task) = withContext(ioDispatcher){
        taskDao.updateCompleted(task.idTask,true)
    }

    override suspend fun completeTask(taskId: String){
        taskDao.updateCompleted(taskId,true)
    }

    override suspend fun activeTask(task:Task) = withContext(ioDispatcher){
        taskDao.updateCompleted(task.idTask,false)
    }

    override suspend fun activeTask(taskId: String){
        taskDao.updateCompleted(taskId,false)
    }

    override suspend fun clearCompletedTask() = withContext<Unit>(ioDispatcher){
        taskDao.deleteCompletedTask()
    }

    override suspend fun deleteAllTask() = withContext(ioDispatcher){
        taskDao.deleteTasks()
    }

    override suspend fun deleteTask(taskId: String){
        taskDao.deleteTaskById(taskId)
    }
}