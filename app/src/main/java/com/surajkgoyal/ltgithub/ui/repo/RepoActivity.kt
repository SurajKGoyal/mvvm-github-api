package com.surajkgoyal.ltgithub.ui.repo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.viewModels
import androidx.lifecycle.Observer

import com.surajkgoyal.ltgithub.R
import com.surajkgoyal.ltgithub.db.model.Repos
import com.surajkgoyal.ltgithub.ui.issue.IssueActivity
import com.surajkgoyal.ltgithub.utils.NetworkUtils

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
        rViewModel.getRepos(user)
        rViewModel.reposLiveData.observe(this, Observer {listResource ->
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

    override fun onItemClicked(repos: Repos) {
        val intent = Intent(this, IssueActivity::class.java)
        intent.putExtra("repo", repos.name)
        intent.putExtra("user",userName.text.toString().trim() )
        intent.putExtra("repoUrl", repos.url)
        intent.putExtra("fullName", repos.fullName)
        startActivity(intent)
    }
}