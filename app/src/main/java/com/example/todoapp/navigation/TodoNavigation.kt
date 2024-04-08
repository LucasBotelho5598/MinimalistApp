package com.example.todoapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.Routes.EDIT_SCREEN_TODO
import com.example.todoapp.Routes.ONBOARDING
import com.example.todoapp.Routes.SCREEN_TODO
import com.example.todoapp.Routes.SIGN_IN
import com.example.todoapp.Routes.SIGN_UP
import com.example.todoapp.Routes.TODO_DEFAULT_ID
import com.example.todoapp.Routes.TODO_ID
import com.example.todoapp.Routes.TODO_ID_ARGS
import com.example.todoapp.TodoAppState
import com.example.todoapp.addtask.AddTasks
import com.example.todoapp.onboarding.Onboarding
import com.example.todoapp.task.TaskScreen
import com.example.todoapp.sign_in.SignIn
import com.example.todoapp.sign_up.SignUp

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun TodoNavigation() {
    val appState = rememberAppState()

    NavHost(
        navController = appState.navController,
        startDestination = ONBOARDING,
    ) {
        TodoGraph(appState)

    }
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        TodoAppState(navController)
    }



@RequiresApi(Build.VERSION_CODES.M)
fun NavGraphBuilder.TodoGraph(appState: TodoAppState) {
    composable(SCREEN_TODO) {
        TaskScreen(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route -> appState.navigate(route) }

        )
    }

    composable(
        route = "$EDIT_SCREEN_TODO$TODO_ID_ARGS",
        arguments = listOf(navArgument(TODO_ID) { defaultValue = TODO_DEFAULT_ID })
    ) {
        AddTasks(
            taskId = it.arguments?.getString(TODO_ID) ?: TODO_DEFAULT_ID,
            popUpScreen = { appState.popUp() },
            restartApp = { route -> appState.clearAndNavigate(route) })
    }

    composable(SIGN_IN) {
        SignIn(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_UP) {
        SignUp(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(ONBOARDING) {
        Onboarding(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }


}