package com.example.todoapp.model.iml

import com.example.todoapp.model.Task
import com.example.todoapp.model.service.AccountService
import com.example.todoapp.model.service.StorageService
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.Firebase
import com.google.firebase.firestore.AggregateSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val auth: AccountService,
) : StorageService {


    @OptIn(ExperimentalCoroutinesApi::class)
    override val tasks: Flow<List<Task>>
        get() =
            auth.currentUser.flatMapLatest { task ->
                Firebase.firestore
                    .collection("Task")
                    .whereEqualTo("userId", task.id)
                    .dataObjects()
            }

    override suspend fun createTask(task: Task) {
        val taskWithUserId = task.copy(userId = auth.currentUserId)
        Firebase.firestore
            .collection("Task")
            .add(taskWithUserId).await()

    }

    override suspend fun readTask(taskId: String): Task? {
        return Firebase.firestore
            .collection("Task")
            .document(taskId).get().await().toObject()

    }

    override suspend fun updateTask(task: Task) {
        Firebase.firestore
            .collection("Task")
            .document(task.id).set(task).await()
    }

    override suspend fun deleteTask(taskId: String) {
        Firebase.firestore
            .collection("Task")
            .document(taskId).delete().await()
    }

    override suspend fun getTaskCompleted(): Int {
        val query = Firebase.firestore.collection("Task").whereEqualTo("completed", true).count()
        return query.get(AggregateSource.SERVER).await().count.toInt()
    }

}




