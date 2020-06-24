package com.surajkgoyal.ltgithub.ui.repo

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.surajkgoyal.ltgithub.db.model.Repos

class RepoItemAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<Repos, RepoItemViewHolder>(RepoListDiffCallback()) {


    interface OnItemClickListener {
        fun onItemClicked(repos: Repos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        return RepoItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        holder.bind(getItem(position)!!, onItemClickListener)
    }

}

class RepoListDiffCallback : DiffUtil.ItemCallback<Repos>() {
    override fun areItemsTheSame(oldItem: Repos, newItem: Repos): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repos, newItem: Repos): Boolean {
        return oldItem == newItem
    }
}