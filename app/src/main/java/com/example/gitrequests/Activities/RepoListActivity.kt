package com.example.gitrequests.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitrequests.Adapters.RepoAdapter
import com.example.gitrequests.Data.Repository.GithubDataRepository
import com.example.gitrequests.R
import com.example.gitrequests.Utils.Constants
import com.example.gitrequests.ViewModels.RepoViewModelFactory
import com.example.gitrequests.ViewModels.ReposViewModel
import com.example.gitrequests.databinding.ActivityRepoListBinding

class RepoListActivity : AppCompatActivity() {

    private lateinit var repoBinding: ActivityRepoListBinding
    private lateinit var repoViewModel: ReposViewModel
    private lateinit var username: String
    private lateinit var repoAdapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoBinding = ActivityRepoListBinding.inflate(layoutInflater)
        setContentView(repoBinding.root)

        supportActionBar?.title = getString(R.string.repo_list)

        username = intent.getStringExtra(Constants.USERNAME_KEY).toString()

        repoViewModel = ViewModelProvider(this, RepoViewModelFactory(GithubDataRepository(), username))[ReposViewModel::class.java]

        repoViewModel.repoList.observe(this, Observer {
            Log.d("REPO", it.toString())
        })

        repoAdapter = RepoAdapter(emptyList()) { username, repoName ->
            val intent = Intent(this, PullRequestsActivity::class.java)
            intent.putExtra(Constants.USERNAME_KEY, username)
            intent.putExtra(Constants.REPO_NAME_KEY, repoName)
            startActivity(intent)
        }

        repoBinding.repoListRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@RepoListActivity)
            adapter = repoAdapter
        }

        observerRepoData()
    }

    private fun observerRepoData() {
        repoViewModel.repoList.observe(this, Observer { repos ->
            if (repos.isEmpty()) {
                repoBinding.zeroRepoTextView.visibility = View.VISIBLE
            } else {
                repoBinding.zeroRepoTextView.visibility = View.GONE
            }
            repoAdapter.updateRepoList(repos)
        })
    }

}