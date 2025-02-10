package com.example.mysecondapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysecondapp.domain.models.User
import com.example.mysecondapp.domain.usecases.AddUserUseCase
import kotlinx.coroutines.launch


class AddUserViewModel(private val addUserUseCase: AddUserUseCase): ViewModel() {
    private fun addUser(user: User){
        // A global coroutine to log statistics every second, must be always active
        viewModelScope.launch {
            addUserUseCase(user)
        }
    }
}