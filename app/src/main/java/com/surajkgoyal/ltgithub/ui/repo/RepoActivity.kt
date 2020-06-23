package com.surajkgoyal.ltgithub.ui.repo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.surajkgoyal.ltgithub.R
import com.surajkgoyal.ltgithub.db.model.Repos
import com.surajkgoyal.ltgithub.ui.issue.IssueActivity
import com.surajkgoyal.ltgithub.utils.NetworkUtils
import com.surajkgoyal.ltgithub.utils.State
import com.surajkgoyal.ltgithub.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_repo.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class RepoActivity : AppCompatActivity(), RepoItemAdapter.OnItemClickListener {

    @ExperimentalCoroutinesApi
    private val rViewModel: RepoViewModel by viewModels()

    private val mAdapter: RepoItemAdapter by lazy { RepoItemAdapter(onItemClickListener = this) }


    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        search_icon.setOnClickListener {
            if (userName.text.toString() != "") {
                initRepos(userName.text.toString())
            } else {

            }
        }

        repo_item_list.adapter = mAdapter


    }



    @ExperimentalCoroutinesApi
    private fun initRepos(user: String) {
        rViewModel.getPosts(user)
        rViewModel.reposLiveData.observe(this, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(true)
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        mAdapter.submitList(state.data.toMutableList())
                        showLoading(false)
                    }
                }
                is State.Error -> {
                    showToast(state.message)
                    showLoading(false)
                }
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
    }

    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this, Observer { isConnected ->
            if (!isConnected) {
               showToast("No Internet")
                }
        })
    }

    override fun onItemClicked(repos: Repos) {
        val intent = Intent(this, IssueActivity::class.java)
        intent.putExtra("repo", repos.name)
        intent.putExtra("user", repos.owner)
    }
}