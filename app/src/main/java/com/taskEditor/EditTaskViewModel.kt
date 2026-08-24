package com.taskEditor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Task
import com.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(private val taskRepository: TaskRepository): ViewModel() {
val tasks: StateFlow<List<Task>> = taskRepository.getTasks()
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )
     fun addTask(task: Task) {
        viewModelScope.launch {
            taskRepository.addTask(task)
        }
    }

     fun updateTask(task: Task) {
        viewModelScope.launch {
            taskRepository.update(task)
        }
    }
}