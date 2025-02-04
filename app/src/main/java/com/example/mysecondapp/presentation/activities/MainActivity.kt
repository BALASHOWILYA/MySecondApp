package com.example.mysecondapp.presentation.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mysecondapp.R
import com.example.mysecondapp.data.repositories.UserRepositoryImpl
import com.example.mysecondapp.databinding.ActivityMainBinding
import com.example.mysecondapp.domain.models.User
import com.example.mysecondapp.domain.usecases.GetUsersUseCase
import com.example.mysecondapp.presentation.fragments.UserProfile
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

        val userProfile: UserProfile = UserProfile()

        if(savedInstanceState == null){
            addFragment(userProfile, R.id.fragment_container)
        }

        userViewModel = ViewModelProvider(this, viewModelFactory)[GetUsersViewModel::class.java]

        binding.buttonId.setOnClickListener {
            lifecycleScope.launchWhenResumed {
                userViewModel.users.collect() { users ->
                    displayUsers(users)

                }
            }

        }
    }



    private fun addFragment(fragment: Fragment, containerId: Int) {
        if (supportFragmentManager.findFragmentById(containerId) == null) {
            supportFragmentManager
                .beginTransaction()
                .add(containerId, fragment)
                .commitAllowingStateLoss()
        }
    }
    private fun replaceFragment(fragment: Fragment, containerId: Int) {
        if (supportFragmentManager.findFragmentById(containerId) == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(containerId, fragment)
                .commitAllowingStateLoss()
        }
    }

    private fun deleteFragment(fragment: Fragment, containerId: Int) {
        if (supportFragmentManager.findFragmentById(containerId) == null) {
            supportFragmentManager
                .beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss()
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

