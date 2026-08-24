package com.repository

import com.Task
import com.database.TaskDao
import com.database.TaskEntity
import com.database.mapToTask
import com.mapToTaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> {

        return taskDao.getTasks().map { taskEntities ->
            taskEntities.map { it.mapToTask() }

        }
    }

    override suspend fun getTask(id: Long?): Task? {

        if (id == null){
            return null
        }
        return taskDao.getTask(id)?.mapToTask()
    }


    override suspend fun addTask(task: Task) {
        taskDao.insert(task.mapToTaskEntity())
    }



    override suspend fun update(task: Task) {
        taskDao.update(task.mapToTaskEntity())

    }

    override suspend fun delete(task: Task) {
        taskDao.delete(task.mapToTaskEntity())

    }
}