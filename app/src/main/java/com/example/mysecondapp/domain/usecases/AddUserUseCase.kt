package com.example.mysecondapp.domain.usecases

import com.example.mysecondapp.domain.models.User
import com.example.mysecondapp.domain.repositories.UserRepository

class AddUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User) {
        return userRepository.addUser(user)
    }
}