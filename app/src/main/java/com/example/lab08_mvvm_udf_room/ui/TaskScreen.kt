package com.example.lab08_mvvm_udf_room.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab08_mvvm_udf_room.viewmodel.TaskViewModel
import com.example.lab08_mvvm_udf_room.data.Task

@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    var showCompletedOnly by remember { mutableStateOf(false) }
    var newTaskTitle by remember { mutableStateOf("") }

    val filteredTasks = tasks.filter {
        if (showCompletedOnly) it.completed else true
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (newTaskTitle.isNotBlank()) {
                        viewModel.addTask(newTaskTitle.trim())
                        newTaskTitle = ""
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                "Tareas",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Input para nueva tarea
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                placeholder = { Text("Agregar una tarea") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para alternar tareas completadas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { showCompletedOnly = !showCompletedOnly }) {
                    Icon(
                        imageVector = if (showCompletedOnly) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = null
                    )
                }
            }

            // Lista de tareas
            LazyColumn {
                items(filteredTasks) { task ->
                    TaskItem(
                        task = task,
                        onDelete = { viewModel.deleteTask(task) },
                        onToggleComplete = {
                            viewModel.toggleTaskCompletion(task)
                        }
                    )
                }
            }
        }
    }
}
