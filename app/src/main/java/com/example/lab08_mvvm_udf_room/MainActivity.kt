package com.example.lab08_mvvm_udf_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.lab08_mvvm_udf_room.data.TaskDatabase
import com.example.lab08_mvvm_udf_room.ui.TaskScreen
import com.example.lab08_mvvm_udf_room.ui.theme.Lab08_MVVM_UDF_RoomTheme
import com.example.lab08_mvvm_udf_room.viewmodel.TaskViewModel
import com.example.lab08_mvvm_udf_room.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "task_db"
        ).build()

        val taskDao = db.taskDao()

        // Crear el ViewModel fuera de setContent
        val factory = TaskViewModelFactory(taskDao)
        val viewModel = ViewModelProvider(this, factory)[TaskViewModel::class.java]

        setContent {
            Lab08_MVVM_UDF_RoomTheme {
                TaskScreen(viewModel)
            }
        }
    }
}
