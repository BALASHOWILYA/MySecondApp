package com.example.mysecondapp.data.repositories

import com.example.mysecondapp.data.models.UserDtr
import com.example.mysecondapp.domain.models.User
import com.example.mysecondapp.domain.repositories.UserRepository

class UserRepositoryImpl: UserRepository {
    val listUsers = mutableListOf(
        UserDtr(id = 1, name = "Harry", password = "whocares", email = "harry#gmail.com"),
        UserDtr(id = 2, name = "Marry", password = "whocares", email = "marry#gmail.com"),
        UserDtr(id = 3, name = "David", password = "whocares", email = "david#gmail.com")
    )



    override suspend fun getUsers(): List<User> {


        return listUsers.map { user ->
            User(
                user.name,
                user.password,
                user.email,

            )
        }
    }

    override suspend fun addUser(user: User) {
        listUsers.add(UserDtr(id = 4, name = user.name, password = user.password, email = user.email))
    }
}