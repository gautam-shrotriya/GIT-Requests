package com.example.gitrequests.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.gitrequests.Data.Repository.GithubDataRepository
import com.example.gitrequests.R
import com.example.gitrequests.Utils.Constants
import com.example.gitrequests.ViewModels.UserViewModel
import com.example.gitrequests.ViewModels.UserViewModelFactory
import com.example.gitrequests.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var userBinding: ActivityUserBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userBinding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(userBinding.root)

        supportActionBar?.title = getString(R.string.user_details)

        username = intent.getStringExtra(Constants.USERNAME_KEY).toString()

        userViewModel = ViewModelProvider(this, UserViewModelFactory(GithubDataRepository(), username))[UserViewModel::class.java]

        observeUserData()
    }

    private fun setOnClicks() {
        userBinding.viewRepositoriesButton.setOnClickListener {
            goToReposActivity(username)
        }
    }

    private fun goToReposActivity(userName: String) {
        val userToRepoIntent = Intent(this@UserActivity, RepoListActivity::class.java)
        userToRepoIntent.putExtra(Constants.USERNAME_KEY, userName)
        startActivity(userToRepoIntent)
    }

    private fun observeUserData() {

//        if (!isNetworkAvailable()) {
//            userBinding.errorUserTextView.text = getString(R.string.no_internet_connection)
//            userBinding.errorUserTextView.visibility = View.VISIBLE
//            hideUserDetailViews()
//            return
//        }

        // Observe user data
        userViewModel.userExists.observe(this, Observer { userExists ->
            if (!userExists) {
                // User does not exist or there was an error
                userBinding.errorUserTextView.text = getString(R.string.user_does_not_exist)
                userBinding.errorUserTextView.visibility = View.VISIBLE
                hideUserDetailViews()
            }
        })

        userViewModel.user.observe(this, Observer { user ->
            user?.let {
                // User data retrieved successfully
                userBinding.errorUserTextView.visibility = View.GONE
                userBinding.usernameTextView.text = user.login
                userBinding.repoCountTextView.text = getString(R.string.public_repo_count, user.publicRepos.toString())
                Glide.with(this)
                    .load(user.avatarUrl)
                    .placeholder(R.drawable.ic_baseline_broken_image)
                    .error(R.drawable.ic_baseline_broken_image)
                    .into(userBinding.userImageView)
                showUserDetailViews()
            }
        })
    }

    private fun hideUserDetailViews() {
        userBinding.userImageView.visibility = View.GONE
        userBinding.usernameTextView.visibility = View.GONE
        userBinding.repoCountTextView.visibility = View.GONE
        userBinding.viewRepositoriesButton.visibility = View.GONE
    }

    private fun showUserDetailViews() {
        userBinding.userImageView.visibility = View.VISIBLE
        userBinding.usernameTextView.visibility = View.VISIBLE
        userBinding.repoCountTextView.visibility = View.VISIBLE
        userBinding.viewRepositoriesButton.visibility = View.VISIBLE
    }

//    private fun isNetworkAvailable(): Boolean {
//        val connectivityManager =
//            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (connectivityManager != null) {
//            val capabilities =
//                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//            if (capabilities != null) {
//                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
//                    return true
//                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
//                    return true
//                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
//                    return true
//                }
//            }
//        }
//        return false
//    }
}