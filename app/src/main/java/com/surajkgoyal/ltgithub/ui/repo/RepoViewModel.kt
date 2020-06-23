package com.surajkgoyal.ltgithub.ui.repo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajkgoyal.ltgithub.db.model.Repos
import com.surajkgoyal.ltgithub.repositories.ReposRepository
import com.surajkgoyal.ltgithub.utils.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class RepoViewModel @ViewModelInject constructor(private val repository: ReposRepository) :
    ViewModel() {


    private val _reposLiveData = MutableLiveData<State<List<Repos>>>()

    val reposLiveData: LiveData<State<List<Repos>>>
        get() = _reposLiveData

    fun getPosts(user:String) {
        viewModelScope.launch {
         repository.getAllRepos(user).collect {
             _reposLiveData.value = it
         }


        }
    }

    fun addUserToRepo(user: String){
        viewModelScope.launch {
            repository
        }
    }
}