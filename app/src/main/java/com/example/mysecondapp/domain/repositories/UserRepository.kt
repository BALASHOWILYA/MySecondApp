package com.example.mysecondapp.domain.repositories

import com.example.mysecondapp.domain.models.User

interface UserRepository {

    suspend fun getUsers(): List<User>
}