package com.example.lab08_mvvm_udf_room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val description: String,
    val isCompleted: Boolean = false
)












