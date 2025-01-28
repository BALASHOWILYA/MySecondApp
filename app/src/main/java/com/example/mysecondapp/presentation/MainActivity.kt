package com.example.mysecondapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mysecondapp.R
import com.example.mysecondapp.data.repositories.UserRepositoryImpl
import com.example.mysecondapp.databinding.ActivityMainBinding
import com.example.mysecondapp.domain.usecases.GetUsersUseCase
import com.example.mysecondapp.presentation.viewmodels.GetUsersViewModel
import com.example.mysecondapp.presentation.viewmodels.UserViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GetUsersViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val userRepository = UserRepositoryImpl()
        val getUsersUseCase = GetUsersUseCase(userRepository)
        val viewModelFactory = UserViewModelFactory(getUsersUseCase = getUsersUseCase)



        viewModel = ViewModelProvider(this, viewModelFactory)[GetUsersViewModel::class.java]



        binding.buttonId.setOnClickListener{
            lifecycleScope.launch {
                viewModel.user.collect{ list ->
                    binding.nameListId.text = list.joinToString(separator = "\n") { it.name }
                }
            }
        }





        Log.d("mlog", "onCreate")


    }




}