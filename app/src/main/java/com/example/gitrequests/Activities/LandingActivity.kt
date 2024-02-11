package com.example.gitrequests.Activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
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
            if(!isNetworkAvailable()) {
                Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show()
            } else {
                val userName: String = landingBinding.etUserName.text.toString()

                if(landingViewModel.isValid(userName)) {
                    landingViewModel.setUsername(userName)
                    goToUserDetailsActivity(userName)
                } else {
                    Toast.makeText(this, R.string.msg_invalid_username, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeUsername() {
        landingViewModel.username.observe(this, Observer { username ->
            landingBinding.etUserName.setText(username)
        })
    }

    private fun goToUserDetailsActivity(userName: String) {
        val landingToUserIntent = Intent(this@LandingActivity, UserActivity::class.java)
        landingToUserIntent.putExtra(Constants.USERNAME_KEY, userName)
        startActivity(landingToUserIntent)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

}