package com.example.secondwar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.Task
import com.tasklist.TaskListViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(
    //tasks: SnapshotStateList<Task>,
    onBackClick: () -> Unit,
    //onAddTask :(String) -> Unit
) {

    val viewModel: TaskListViewModel = hiltViewModel()
    val tasks by viewModel.tasks.collectAsState()

    var task by remember { mutableStateOf("") }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val datePickerState = rememberDatePickerState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFBBDEFB))
            .systemBarsPadding()

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),  // به جای Spacer
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back", tint = Color.DarkGray, modifier = Modifier
                        .size(32.dp)
                        .clickable { onBackClick() }
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "Add Task", color = Color.Black, fontSize = 32.sp)

            }
            TextField(
                value = task,
                onValueChange = { task = it },
                placeholder = { Text(text = "Add Task") },
                modifier = Modifier.fillMaxWidth()

            )
            OutlinedButton(onClick = { showDatePickerDialog = true }) {
                Text(
                    text = if (selectedDate == null) {
                        "Select task date"
                    } else {
                        selectedDate.toString()

                    }
                )

            }
            Button(
                onClick = {
                    if (task.isNotBlank()) {
                        viewModel.addTask(
                            Task(
                                title = task,
                                dueDate = selectedDate,
                                isCompleted = false
                            )
                        )
                        // tasks.add()
                        // onAddTask(task)
                        // task = ""
                        onBackClick()      //برای اینکه برگرده صفحه تسک لیست


                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFFFE0B2))
            ) {
                Text(text = "Add new task", color = Color.Black)

            }
        }

        if (showDatePickerDialog) {

            DatePickerDialog(
                onDismissRequest = { showDatePickerDialog = false },
                confirmButton = {

                    TextButton(
                        onClick = {
                            showDatePickerDialog = false
                            selectedDate = datePickerState.selectedDateMillis?.let { millis ->
                                Instant.ofEpochMilli(millis)
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                            }

                        }
                    ) {
                        Text("Ok")
                    }
                },
            ) {
                DatePicker(
                    state = datePickerState
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {

   // val tasks = remember { mutableStateListOf<Task>() }

    ToDoListScreen(
//tasks = tasks,
        onBackClick = {},
        // onAddTask = {}
    )

}