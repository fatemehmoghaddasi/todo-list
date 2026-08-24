package com

import com.database.TaskEntity
import kotlinx.serialization.descriptors.PrimitiveKind
import java.time.LocalDate


data class Task(
    val id: Long ? = null ,     //= System.currentTimeMillis(),
    val title : String,
    var isCompleted : Boolean = false ,
    val dueDate : LocalDate? = null

)
fun Task.mapToTaskEntity() = TaskEntity(
    id = id ?: 0 ,
    title = title,
    isCompleted = isCompleted,
    dueDate = dueDate
)