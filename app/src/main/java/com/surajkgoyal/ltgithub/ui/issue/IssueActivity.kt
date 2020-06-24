package com.surajkgoyal.ltgithub.ui.issue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.surajkgoyal.ltgithub.R
import com.surajkgoyal.ltgithub.utils.NetworkUtils
import com.surajkgoyal.ltgithub.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_issue.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class IssueActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    private val iViewModel: IssueViewModel by viewModels()

    private var mAdapter = IssueItemAdapter()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue)

        val user = intent.extras?.getString("user")
        val repo = intent.extras?.getString("repo")

        val fullName = intent.extras?.getString("fullName")

        val repoUrl = intent.extras?.getString("repoUrl")

        if (repo != null) {
            if (user != null) {
                if (repoUrl != null) {
                    initIssues(user, repo, repoUrl)
                }
                println("++++++++++++____________${user} -- ${repo}______________________=")
            }
        }

        issue_list.apply {
            adapter = mAdapter
        }



    }



    @ExperimentalCoroutinesApi
    private fun initIssues(user: String, repo: String, repoUrl: String) {
        iViewModel.getIssues(user, repo, repoUrl)
        iViewModel.issuesLiveData.observe(this, Observer {listResource ->
            // we don't need any null checks here for the adapter since LiveData guarantees that
            // it won't call us if fragment is stopped or not started.
            if (listResource?.data != null) {
                mAdapter.submitList(listResource.data)
            } else {
                mAdapter.submitList(emptyList())
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