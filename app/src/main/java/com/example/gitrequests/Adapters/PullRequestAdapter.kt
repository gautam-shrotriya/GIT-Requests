package com.example.gitrequests.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrequests.Data.Models.PrModel
import com.example.gitrequests.databinding.ItemPullRequestBinding
import java.text.SimpleDateFormat
import java.util.*

class PullRequestAdapter(private var prList: List<PrModel>) : RecyclerView.Adapter<PullRequestAdapter.PRViewHolder>() {

    class PRViewHolder(binding: ItemPullRequestBinding) : RecyclerView.ViewHolder(binding.root) {
        private val titleTextView: TextView = binding.prTitleTextView
        private val usernameTextView: TextView = binding.usernameTextView
        private val prNumberTextView: TextView = binding.prNumberTextView
        private val createdDateTextView: TextView = binding.createdDateTextView
        private val closedDateTextView: TextView = binding.closedDateTextView

        private fun formatDate(inputDate: String?): String {
            if (inputDate.isNullOrEmpty()) return ""
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val date = inputFormat.parse(inputDate)
            return outputFormat.format(date)
        }

        fun bind(pr: PrModel) {
            titleTextView.text = pr.title
            usernameTextView.text = pr.owner.login
            prNumberTextView.text = pr.pullRequestId.toString()
            createdDateTextView.text = formatDate(pr.createdDate)
            closedDateTextView.text = formatDate(pr.closedDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PRViewHolder {
        val binding = ItemPullRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PullRequestAdapter.PRViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PRViewHolder, position: Int) {
        val currentItem = prList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return prList.size
    }

    fun updatePrList(newPrList: List<PrModel>) {
        prList = newPrList
        notifyDataSetChanged()
    }
}