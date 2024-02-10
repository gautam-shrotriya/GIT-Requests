package com.example.gitrequests.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.gitrequests.R
import com.example.gitrequests.ViewModels.LandingViewModel
import com.example.gitrequests.ViewModels.ReposViewModel
import com.example.gitrequests.databinding.ActivityLandingBinding
import com.example.gitrequests.databinding.ActivityRepoListBinding

class RepoListActivity : AppCompatActivity() {

    private lateinit var repoBinding: ActivityRepoListBinding
    private lateinit var repoViewModel: ReposViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoBinding = ActivityRepoListBinding.inflate(layoutInflater)
        setContentView(repoBinding.root)

        supportActionBar?.title = getString(R.string.repo_list)

        repoViewModel = ViewModelProvider(this)[ReposViewModel::class.java]
    }


}