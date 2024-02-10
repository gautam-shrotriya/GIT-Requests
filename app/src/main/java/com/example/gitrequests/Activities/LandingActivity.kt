package com.example.gitrequests.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gitrequests.R
import com.example.gitrequests.Utils.Constants
import com.example.gitrequests.ViewModels.LandingViewModel
import com.example.gitrequests.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {

    private lateinit var landingBinding: ActivityLandingBinding
    private lateinit var landingViewModel: LandingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        landingBinding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(landingBinding.root)

        supportActionBar?.title = getString(R.string.app_name)

        landingViewModel = ViewModelProvider(this)[LandingViewModel::class.java]

        setOnClicks()
        observeUsername()
    }

    private fun setOnClicks() {
        landingBinding.btnGetRepos.setOnClickListener {
            val userName: String = landingBinding.etUserName.text.toString()
            if(landingViewModel.isValid(userName)) {
                landingViewModel.setUsername(userName)
                goToReposActivity(userName)
            } else {
                Toast.makeText(this, R.string.msg_invalid_username, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeUsername() {
        landingViewModel.username.observe(this, Observer { username ->
            landingBinding.etUserName.setText(username)
        })
    }

    private fun goToReposActivity(userName: String) {
        val landingToRepoIntent = Intent(this@LandingActivity, RepoListActivity::class.java)
        landingToRepoIntent.putExtra(Constants.USERNAME_KEY, userName)
        startActivity(landingToRepoIntent)
    }

}