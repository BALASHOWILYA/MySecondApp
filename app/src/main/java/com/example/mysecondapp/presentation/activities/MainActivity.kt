package com.example.mysecondapp.presentation.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mysecondapp.data.repositories.UserRepositoryImpl
import com.example.mysecondapp.databinding.ActivityMainBinding
import com.example.mysecondapp.domain.models.User
import com.example.mysecondapp.domain.usecases.GetUsersUseCase
import com.example.mysecondapp.presentation.viewmodels.GetUsersViewModel
import com.example.mysecondapp.presentation.viewmodels.UserViewModelFactory

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: GetUsersViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userRepository = UserRepositoryImpl()
        val getUsersUseCase = GetUsersUseCase(userRepository)
        val viewModelFactory = UserViewModelFactory(getUsersUseCase = getUsersUseCase)

        userViewModel = ViewModelProvider(this, viewModelFactory)[GetUsersViewModel::class.java]

        binding.buttonId.setOnClickListener {
            lifecycleScope.launchWhenResumed {
                userViewModel.users.collect() { users ->
                    displayUsers(users)

                }
            }

        }
    }

    private fun displayUsers(users: List<User>) {
        val sb = StringBuilder()
        users.forEach {
                user ->
            sb.append("${user.name}\n")
        }
        binding.nameListId.text = sb.toString()

    }
}

