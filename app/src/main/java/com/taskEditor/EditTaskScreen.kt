package com.taskEditor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EditTaskScreen(
    //onEditTask:(Task) -> Unit,
    taskId : Long?,

    onBackClick :() -> Unit,

    //onAddTask: (String) -> Unit,


    ){

    val viewModel : EditTaskViewModel = hiltViewModel()
    val tasks by viewModel.tasks.collectAsState()

    var editTask by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var showDatePickerDialog by remember { mutableStateOf(false) }


    /*for(task in tasks){
        if(task.id == taskId ){
            editTask = task.title

        }
    }*/
     val taskToEdit = tasks.find{ it.id == taskId}
    /*taskToEdit?.let { viewModel.updateTask(it.copy(
        title = editTask,
        dueDate = selectedDate
        )
    )
    }*/
    LaunchedEffect(taskToEdit) {
        taskToEdit?.let {
            editTask = it.title
            selectedDate = it.dueDate
        }
    }

    Scaffold(
        containerColor = Color(0xFFBBDEFB),
        topBar = {
            TopAppBar(
                title = { Text( "Edit task") },
                navigationIcon = {
                    IconButton(onClick = {onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFBBDEFB))
            )
        }
    ) { innerPadding ->

        Column(

            modifier = Modifier
                .fillMaxSize(),

            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


                TextField(
                    value = editTask,
                    onValueChange = { editTask = it },
                    placeholder = { Text(text = "Enter your task") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                        .padding(16.dp)
                )
            OutlinedButton(

                onClick = {showDatePickerDialog = true}

                )
            {
                Text(
                    text = if (selectedDate == null) {
                        "Select task date"
                    } else{
                        selectedDate.toString()
                    }
                )

        }
                Button(

                    onClick = {
                        //viewModel.addTask(Task(title = editTask))
                        if(taskId != null && taskToEdit != null ){
                            viewModel.updateTask(taskToEdit.copy(title = editTask, dueDate = selectedDate) )
                        }
                        onBackClick()
                              },

                    colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFFFE0B2))
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Edit task",color = Color.Black)
                }

            }

        }
    if (showDatePickerDialog){
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = {showDatePickerDialog = false},
            confirmButton = {
                TextButton(
                    onClick = {showDatePickerDialog = false
                    selectedDate = datePickerState.selectedDateMillis?.let { millies ->
                        Instant.ofEpochMilli(millies)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                        }
                    }

                ){
                    Text("ok")
                }
            },
        ){
            DatePicker(
                state = datePickerState
            )
        }
    }
    }




@Composable
@Preview
fun editTaskPreview(){
    EditTaskScreen(
        onBackClick = {},
        //onAddTask = {},
        taskId = 1
       // onEditTask = {}
    )
}