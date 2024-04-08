package com.example.todoapp.model.service

import com.example.todoapp.model.Task
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val tasks: Flow<List<Task>>

    suspend fun createTask(task: Task)
    suspend fun readTask(taskId: String): Task?
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(taskId: String)
    suspend fun getTaskCompleted(): Int
}