package com.example.mysecondapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    val users: StateFlow<List<User>> = _users

    init{
        loadUsers()
    }
    private fun loadUsers(){
        // A global coroutine to log statistics every second, must be always active
        viewModelScope.launch {
            try{
                val result = getUsersUseCase()
                Log.d("list_of_users", result.toString())
                _users.value = result

            } catch (e: Exception){
                Log.d("tag", e.toString())
            }

        }
    }



}