package com.surajkgoyal.ltgithub.ui.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surajkgoyal.ltgithub.databinding.RepoItemsBinding
import com.surajkgoyal.ltgithub.db.model.Repos

class RepoItemViewHolder(val binding: RepoItemsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Repos, onItemClickListener: RepoItemAdapter.OnItemClickListener? = null) {
        binding.repo = item

        onItemClickListener?.let {listener ->
            binding.root.setOnClickListener {
                listener.onItemClicked(item)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): RepoItemViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RepoItemsBinding.inflate(layoutInflater, parent, false)

            return RepoItemViewHolder(binding)
        }
    }
}