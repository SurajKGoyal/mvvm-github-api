package com.surajkgoyal.ltgithub.ui.issue

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.surajkgoyal.ltgithub.db.model.Issues
import com.surajkgoyal.ltgithub.db.model.Repos

class IssueItemAdapter() :
    ListAdapter<Issues, IssueItemViewHolder>(RepoListDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueItemViewHolder {
        return IssueItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: IssueItemViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

}

class RepoListDiffCallback : DiffUtil.ItemCallback<Issues>() {
    override fun areItemsTheSame(oldItem: Issues, newItem: Issues): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Issues, newItem: Issues): Boolean {
        return oldItem == newItem
    }
}