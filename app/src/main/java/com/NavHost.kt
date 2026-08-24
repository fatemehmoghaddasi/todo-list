package com


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.tasklist.TaskListScreen
import com.example.secondwar.ToDoListScreen
import com.taskEditor.EditTaskScreen
import kotlinx.serialization.Serializable



@Composable
fun Nav() {

   // val tasks = remember { mutableStateListOf<Task>() }
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TaskListRoute
    ) {
        composable<TaskListRoute> {
            //val viewModel : TaskListViewModel = hiltViewModel()
            TaskListScreen(
                // tasks = tasks,
                onAddClick = {
                    navController.navigate(ToDoListRoute)
                },
                onEditTask = { task ->
                    navController.navigate(        // با کلیک روی تسک ، تسک همراه با id به صفحه edittask  منتقل میشه
                        EditTaskRoute(task.id)
                    )
                },
                //onDeleteTask = {},
               // onIsCompletedChange = {isCompleted -> viewModel.updateTask(task : Task) }

            )
        }

        composable<ToDoListRoute> {
            ToDoListScreen(
                //tasks = tasks,
                //onAddTask = { title ->
                    //viewModel.addTask(Task(title = title))

                 //   navController.popBackStack()
              //  },
                onBackClick = {
                    navController.popBackStack()
                }
            )
            }
            composable<EditTaskRoute> { backStackEntry ->
                val taskId = backStackEntry.toRoute<EditTaskRoute>().taskId
                EditTaskScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    taskId = taskId
                    //onAddTask = {navController.popBackStack()}
                )

            }
        }
    }


@Serializable
data object TaskListRoute

@Serializable
data object ToDoListRoute

@Serializable
data class EditTaskRoute (val taskId : Long? = null)