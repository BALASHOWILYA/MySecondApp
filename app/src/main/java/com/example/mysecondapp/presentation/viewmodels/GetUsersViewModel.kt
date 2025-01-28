package com.example.mysecondapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysecondapp.data.models.UserDtr
import com.example.mysecondapp.data.repositories.UserRepositoryImpl
import com.example.mysecondapp.domain.models.User
import com.example.mysecondapp.domain.usecases.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GetUsersViewModel(private val getUsersUseCase: GetUsersUseCase): ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val user: StateFlow<List<User>> =_users.asStateFlow()

    init{
        loadUsers()
    }
    private fun loadUsers(){
        // A global coroutine to log statistics every second, must be always active
        viewModelScope(Dispatchers.IO).launch {
            val result = getUsersUseCase()
            Log.d("list_of_users", result.toString())
            _users.value = listOf(
                User(name = "Harry", password = "whocares", email = "harry#gmail.com"),
                User(name = "Marry", password = "whocares", email = "marry#gmail.com"),
                User(name = "David", password = "whocares", email = "david#gmail.com")
            )
        }
    }



}