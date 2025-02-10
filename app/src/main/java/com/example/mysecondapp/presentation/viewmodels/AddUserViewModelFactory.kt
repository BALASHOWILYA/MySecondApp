package com.example.mysecondapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mysecondapp.domain.usecases.AddUserUseCase
import com.example.mysecondapp.domain.usecases.GetUsersUseCase

@Suppress("UNCHECKED_CAST")
class AddUserViewModelFactory(private val addUserUseCase: AddUserUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddUserViewModel(addUserUseCase) as T
    }
}