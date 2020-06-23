package com.surajkgoyal.ltgithub.ui.issue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.surajkgoyal.ltgithub.R
import com.surajkgoyal.ltgithub.ui.repo.RepoItemAdapter
import com.surajkgoyal.ltgithub.utils.NetworkUtils
import com.surajkgoyal.ltgithub.utils.State
import com.surajkgoyal.ltgithub.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_issue.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class IssueActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    private val iViewModel: IssueViewModel by viewModels()

    private lateinit var mAdapter: IssueItemAdapter

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue)

        val user = intent.extras?.getString("user")
        val repo = intent.extras?.getString("repo")

        if (repo != null) {
            if (user != null) {
                iViewModel.getIssues(user, repo)
            }
        }

        issue_list.apply {
            adapter = mAdapter
        }



    }

    @ExperimentalCoroutinesApi
    private fun initRepos(user: String, repo: String) {
        iViewModel.getIssues(user, repo)
        iViewModel.reposLiveData.observe(this, Observer { state ->
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
}