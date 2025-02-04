package com.example.mysecondapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mysecondapp.domain.usecases.GetUsersUseCase

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(private val getUsersUseCase: GetUsersUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GetUsersViewModel(getUsersUseCase) as T
    }
}