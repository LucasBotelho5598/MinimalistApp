package com.example.todoapp.sign_up

import com.example.todoapp.Routes.ONBOARDING
import com.example.todoapp.Routes.SCREEN_TODO
import com.example.todoapp.Routes.SIGN_IN
import com.example.todoapp.Routes.SIGN_UP
import com.example.todoapp.model.service.AccountService
import com.example.todoapp.onboarding.Onboarding
import com.example.todoapp.viewmodel.TodoAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
) : TodoAppViewModel() {

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun updateConfirmPassword(newConfirm: String) {
        confirmPassword.value = newConfirm
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            if (password.value != confirmPassword.value) {
                throw Exception("Passwords wrong")
            }
            accountService.signUp(email.value, password.value)
            openAndPopUp(SCREEN_TODO, SIGN_UP)

        }
    }



}