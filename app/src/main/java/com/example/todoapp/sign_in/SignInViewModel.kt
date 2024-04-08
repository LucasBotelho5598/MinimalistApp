package com.example.todoapp.sign_in

import com.example.todoapp.Routes.SCREEN_TODO
import com.example.todoapp.Routes.SIGN_IN
import com.example.todoapp.Routes.SIGN_UP
import com.example.todoapp.model.service.AccountService
import com.example.todoapp.viewmodel.TodoAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService
): TodoAppViewModel(){
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            accountService.signIn(email.value, password.value)
            openAndPopUp(SCREEN_TODO, SIGN_IN)
        }
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(SIGN_UP, SIGN_IN)
    }

}