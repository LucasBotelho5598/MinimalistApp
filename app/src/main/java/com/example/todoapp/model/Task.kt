package com.example.todoapp.model

import com.google.firebase.firestore.DocumentId


data class Task(
    @DocumentId val id: String = "",
    val userId: String = "",
    val title: String = "",
    val completed: Boolean = false


)








