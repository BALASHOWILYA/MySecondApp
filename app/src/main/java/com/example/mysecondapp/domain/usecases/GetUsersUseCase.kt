package com.example.mysecondapp.domain.usecases

import com.example.mysecondapp.domain.models.User
import com.example.mysecondapp.domain.repositories.UserRepository

class GetUsersUseCase(private val userRepository: UserRepository) {
     suspend operator fun invoke(): List<User> {
        return userRepository.getUsers()
    }
}