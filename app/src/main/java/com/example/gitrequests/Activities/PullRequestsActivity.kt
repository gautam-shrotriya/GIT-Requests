package com.example.gitrequests.Activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitrequests.Adapters.PullRequestAdapter
import com.example.gitrequests.Data.Repository.GithubDataRepository
import com.example.gitrequests.R
import com.example.gitrequests.Utils.Constants
import com.example.gitrequests.ViewModels.PrViewModelFactory
import com.example.gitrequests.ViewModels.PullRequestViewModel
import com.example.gitrequests.databinding.ActivityPullRequestsBinding

class PullRequestsActivity : AppCompatActivity() {

    private lateinit var prBinding: ActivityPullRequestsBinding
    private lateinit var prViewModel: PullRequestViewModel
    private lateinit var username: String
    private lateinit var repoName: String
    private lateinit var prAdapter: PullRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prBinding = ActivityPullRequestsBinding.inflate(layoutInflater)
        setContentView(prBinding.root)

        supportActionBar?.title = getString(R.string.pull_req_list)

        username = intent.getStringExtra(Constants.USERNAME_KEY).toString()
        repoName = intent.getStringExtra(Constants.REPO_NAME_KEY).toString()

        prViewModel = ViewModelProvider(this, PrViewModelFactory(GithubDataRepository(), username, repoName))[PullRequestViewModel::class.java]

        prAdapter = PullRequestAdapter(emptyList())
        prBinding.prRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@PullRequestsActivity)
            adapter = prAdapter
        }

        observePRData()

//        prViewModel.prList.observe(this, Observer {
//            Log.d("PR", it.toString())
//        })
    }

    private fun observePRData() {
        prViewModel.prList.observe(this, Observer { prList ->

            if (prList.isNullOrEmpty()) {
                prBinding.zeroPRTextView.visibility = View.VISIBLE
                prBinding.zeroPRTextView.text = getString(R.string.zero_pull_requests)
            } else {
                prBinding.zeroPRTextView.visibility = View.GONE
            }
            prAdapter.updatePrList(prList)
        })
    }
}