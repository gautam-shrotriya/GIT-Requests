package com.example.gitrequests.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrequests.Data.Models.Repo
import com.example.gitrequests.databinding.ItemRepoListBinding

class RepoAdapter(private var repoList: List<Repo>, private val onItemClick: (username: String, repoName: String) -> Unit) :
    RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

        class RepoViewHolder(private val binding: ItemRepoListBinding) : RecyclerView.ViewHolder(binding.root) {
            private val titleTextView: TextView = binding.titleTextView
            private val starsTextView: TextView = binding.starsTextView
            private val watchersTextView: TextView = binding.watchersTextView

            fun bind(repo: Repo) {
                titleTextView.text = repo.name
                starsTextView.text = repo.stargazersCount.toString()
                watchersTextView.text = repo.watchersCount.toString()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val currentItem = repoList[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            onItemClick(currentItem.owner.login, currentItem.name)
        }
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    fun updateRepoList(newRepoList: List<Repo>) {
        repoList = newRepoList
        notifyDataSetChanged()
    }
}