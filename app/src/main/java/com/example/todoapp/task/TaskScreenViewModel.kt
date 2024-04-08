package com.example.todoapp.task

import com.example.todoapp.Routes.EDIT_SCREEN_TODO
import com.example.todoapp.Routes.ONBOARDING
import com.example.todoapp.Routes.TODO_DEFAULT_ID
import com.example.todoapp.Routes.TODO_ID
import com.example.todoapp.model.Task
import com.example.todoapp.model.service.AccountService
import com.example.todoapp.model.service.StorageService
import com.example.todoapp.viewmodel.TodoAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,

) : TodoAppViewModel() {

    val tasks = storageService.tasks

    fun initialize(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.currentUser.collect { user ->
                if (user == null) restartApp(ONBOARDING)
            }
        }
    }

    fun onAddClick(openScreen: (String) -> Unit) {
        openScreen("$EDIT_SCREEN_TODO?$TODO_ID=$TODO_DEFAULT_ID")
    }

    fun onTaskClick(openScreen: (String) -> Unit, task: Task) {
        openScreen("$EDIT_SCREEN_TODO?$TODO_ID=${task.id}")
    }


    fun onDeleteClick(task: Task){
        launchCatching {
            storageService.deleteTask(task.id)
        }

    }



    fun onTaskCheckChange(task: Task){
        launchCatching {
            storageService.updateTask(task.copy(completed = !task.completed))

        }
    }

}