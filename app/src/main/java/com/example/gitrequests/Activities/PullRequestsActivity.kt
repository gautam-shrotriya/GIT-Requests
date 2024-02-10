package com.example.gitrequests.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.gitrequests.R
import com.example.gitrequests.ViewModels.PullRequestViewModel
import com.example.gitrequests.ViewModels.ReposViewModel
import com.example.gitrequests.databinding.ActivityPullRequestsBinding
import com.example.gitrequests.databinding.ActivityRepoListBinding

class PullRequestsActivity : AppCompatActivity() {

    private lateinit var prBinding: ActivityPullRequestsBinding
    private lateinit var prViewModel: PullRequestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prBinding = ActivityPullRequestsBinding.inflate(layoutInflater)
        setContentView(prBinding.root)

        supportActionBar?.title = getString(R.string.pull_req_list)

        prViewModel = ViewModelProvider(this)[PullRequestViewModel::class.java]
    }
}