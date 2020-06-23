package com.surajkgoyal.ltgithub.ui.issue

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajkgoyal.ltgithub.db.model.Issues
import com.surajkgoyal.ltgithub.db.model.Repos
import com.surajkgoyal.ltgithub.repositories.IssuesRepository
import com.surajkgoyal.ltgithub.repositories.ReposRepository
import com.surajkgoyal.ltgithub.utils.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class IssueViewModel @ViewModelInject constructor(private val repository: IssuesRepository) :
    ViewModel() {


    private val _reposLiveData = MutableLiveData<State<List<Issues>>>()

    val reposLiveData: LiveData<State<List<Issues>>>
        get() = _reposLiveData

    fun getIssues(user:String, repo: String) {
        viewModelScope.launch {
         repository.getAllIssues(user, repo).collect {
             _reposLiveData.value = it
         }


        }
    }
}