package com.example.gitrequests.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.gitrequests.R
import com.example.gitrequests.ViewModels.ReposViewModel
import com.example.gitrequests.ViewModels.UserViewModel
import com.example.gitrequests.databinding.ActivityRepoListBinding
import com.example.gitrequests.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var userBinding: ActivityUserBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userBinding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(userBinding.root)

        supportActionBar?.title = getString(R.string.user_details)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

    }
}