package com.surajkgoyal.ltgithub.ui.issue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surajkgoyal.ltgithub.databinding.IssueItemsBinding
import com.surajkgoyal.ltgithub.databinding.RepoItemsBinding
import com.surajkgoyal.ltgithub.db.model.Issues
import com.surajkgoyal.ltgithub.db.model.Repos

class IssueItemViewHolder(val binding: IssueItemsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Issues) {
        binding.issue = item

    }

    companion object {
        fun from(parent: ViewGroup): IssueItemViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = IssueItemsBinding.inflate(layoutInflater, parent, false)

            return IssueItemViewHolder(binding)
        }
    }
}