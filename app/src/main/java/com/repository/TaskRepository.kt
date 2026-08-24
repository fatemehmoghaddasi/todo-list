package com.repository

import com.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Flow<List<Task>>

    suspend fun getTask(id : Long?): Task?

   suspend fun addTask(task : Task)

   suspend fun update(task: Task)

   suspend fun delete(task: Task)

}