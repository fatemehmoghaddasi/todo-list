package com.tasklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.Task
import java.time.LocalDate


@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun TaskListScreen(
    //tasks : SnapshotStateList<Task>,

    onAddClick :() -> Unit,
    onEditTask: (Task) -> Unit,
   //onDeleteTask :(Task) -> Unit,
    //onIsCompletedChange : (Boolean) -> Unit

) {

    val viewModel : TaskListViewModel = hiltViewModel()
    val tasks by viewModel.tasks.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task List") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFBBDEFB))
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddClick() }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add"
                )
            }
        },
        containerColor = Color(0xFFBBDEFB)


    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {

            items(tasks) { task ->
                val today = remember { LocalDate.now() }
                val dueDateColor = if (task.dueDate != null && task.dueDate < today) {
                    Color(0XFFFF9800)
                }else{
                    Color(0xFF666666)
                }

                val menuExpanded = remember { mutableStateOf(false)}

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = cardColors(containerColor = Color(0XFFFFE0B2))
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = task.isCompleted,
                            onCheckedChange = { viewModel.updateTask(task.copy(isCompleted = it)) }   // نسخه جدید از تسک و تغییر isCompleted
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f
                                )
                        ) {
                            Text(
                                text = task.title,
                                //modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = task.dueDate?.toString() ?: "No date",
                                color = (dueDateColor)
                            )
                        }
                        Box {

                            IconButton(onClick = { menuExpanded.value = true }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.MoreVert,
                                    contentDescription = "MoreVert"
                                )
                            }
                            DropdownMenu(
                                expanded = menuExpanded.value,
                                onDismissRequest = { menuExpanded.value = false }

                            ) {
                                DropdownMenuItem(
                                    text = {
                                        Row {
                                            Icon(
                                                imageVector = Icons.Filled.Edit,
                                                contentDescription = "Edit"
                                            )


                                            Text("Edit")
                                        }
                                    },

                                    onClick = {
                                        menuExpanded.value = false

                                        onEditTask(task)

                                    }
                                )

                                DropdownMenuItem(
                                    text = {
                                        Row {
                                            Icon(
                                                imageVector = Icons.Filled.Delete,
                                                contentDescription = "Delete"
                                            )

                                            Text("Delete")

                                        }
                                    },
                                    onClick = {
                                        menuExpanded.value = false
                                        viewModel.deleteTask(task)
                                        //onDeleteTask(task)

                                    }
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}


@Preview
@Composable
fun AppPreview2() {

       // val tasks = remember { mutableStateListOf<Task>()}

        TaskListScreen(
//tasks = tasks,
 onAddClick = {},
 onEditTask = {}
 //onIsCompletedChange = {},
 //onDeleteTask = {}
 )

}

