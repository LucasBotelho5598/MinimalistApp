package com.example.todoapp.addtask


import com.example.todoapp.Routes.ONBOARDING
import com.example.todoapp.Routes.TODO_DEFAULT_ID
import com.example.todoapp.model.Task
import com.example.todoapp.model.service.AccountService
import com.example.todoapp.model.service.StorageService
import com.example.todoapp.viewmodel.TodoAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class TasksAddViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
) : TodoAppViewModel() {

    val task = MutableStateFlow(DEFAULT_NOTE)

    fun initialize(taskId: String, restartApp: (String) -> Unit) {
        launchCatching {
            task.value = storageService.readTask(taskId) ?: DEFAULT_NOTE
        }
        observeAuthenticationState(restartApp)


    }

    private fun observeAuthenticationState(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.currentUser.collect { user ->
                if (user == null) restartApp(ONBOARDING)
            }
        }
    }

    fun onTitleChange(newValue: String) {
        task.value = task.value.copy(title = newValue)
    }

    /*fun onDescriptionChange(newValue: String) {
        task.value = task.value.copy(description = newValue)
    }*/

    fun saveTask(popUpScreen: () -> Unit) {
        launchCatching {
            if (task.value.id == TODO_DEFAULT_ID) {
                storageService.createTask(task.value)
            } else {
                storageService.updateTask(task.value)
            }
        }
        popUpScreen()
    }

    companion object {
        private val DEFAULT_NOTE = Task(TODO_DEFAULT_ID, "TodoApp")
    }


}


