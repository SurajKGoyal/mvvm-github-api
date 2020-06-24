package com.surajkgoyal.ltgithub.ui.repo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajkgoyal.ltgithub.db.model.Repos
import com.surajkgoyal.ltgithub.repositories.ReposRepository
import com.surajkgoyal.ltgithub.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class RepoViewModel @ViewModelInject constructor(private val repository: ReposRepository) :
    ViewModel() {


    private val _reposLiveData = MutableLiveData<Resource<List<Repos>>>()

    lateinit var reposLiveData: LiveData<Resource<List<Repos>>>

    fun getRepos(user:String) {
         reposLiveData = repository.getAllRepos(user)


    }

    fun addUserToRepo(user: String){
        viewModelScope.launch {

        }
    }
}