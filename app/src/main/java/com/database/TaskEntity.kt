package com.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.Task
import java.time.LocalDate

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0 ,
    val title : String,
    var isCompleted : Boolean = false,
    val dueDate : LocalDate?


)
fun TaskEntity.mapToTask() = Task(
    id = id ,
    title = title,
    isCompleted = isCompleted,
    dueDate = dueDate
)

