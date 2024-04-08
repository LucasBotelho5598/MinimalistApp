package com.example.todoapp.onboarding

import com.example.todoapp.Routes.ONBOARDING
import com.example.todoapp.Routes.SCREEN_TODO
import com.example.todoapp.Routes.SIGN_IN
import com.example.todoapp.model.service.AccountService
import com.example.todoapp.viewmodel.TodoAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val accountService: AccountService
) : TodoAppViewModel() {

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser()) openAndPopUp(SIGN_IN, ONBOARDING)
        else openAndPopUp(SIGN_IN, ONBOARDING)
    }
}