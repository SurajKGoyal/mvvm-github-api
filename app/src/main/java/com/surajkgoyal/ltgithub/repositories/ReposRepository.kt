package com.surajkgoyal.ltgithub.repositories

import androidx.lifecycle.LiveData
import com.surajkgoyal.ltgithub.AppExecutors
import com.surajkgoyal.ltgithub.api.ApiResponse
import com.surajkgoyal.ltgithub.api.GithubService
import com.surajkgoyal.ltgithub.db.model.Repos
import com.surajkgoyal.ltgithub.db.dao.ReposDao
import com.surajkgoyal.ltgithub.utils.RateLimiter
import com.surajkgoyal.ltgithub.utils.Resource
import com.surajkgoyal.ltgithub.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReposRepository @Inject constructor(
    val reposDao: ReposDao,
    val githubService: GithubService,
    val appExecutors: AppExecutors
) {

    /**
     * Fetched the repos from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)
    @ExperimentalCoroutinesApi
    fun getAllRepos(owner: String): LiveData<Resource<List<Repos>>> {
        return object : NetworkBoundResource<List<Repos>, List<Repos>>(appExecutors) {

            override fun saveCallResult(response: List<Repos>){
                reposDao.insertRepos(response)

            }


            override fun shouldFetch(data: List<Repos>?): Boolean {

                return data == null || data.isEmpty() || repoListRateLimit.shouldFetch(owner)

            }

            override fun loadFromDb() =reposDao.loadRepositories(owner)

            override fun createCall() = githubService.getRepos(owner)
        }.asLiveData()
    }
}