package com.surajkgoyal.ltgithub.repositories

import androidx.lifecycle.LiveData
import com.surajkgoyal.ltgithub.AppExecutors
import com.surajkgoyal.ltgithub.api.GithubService
import com.surajkgoyal.ltgithub.db.dao.IssuesDao
import com.surajkgoyal.ltgithub.db.model.Issues
import com.surajkgoyal.ltgithub.db.model.Repos
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

@ExperimentalCoroutinesApi
@Singleton
class IssuesRepository @Inject constructor(
    val issuesDao: IssuesDao,
    val githubService: GithubService,
    val appExecutors: AppExecutors
) {

    /**
     * Fetched the repos from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun getAllIssues(user: String, repo:String, repoUrl:String): LiveData<Resource<List<Issues>>> {
        return object : NetworkBoundResource<List<Issues>, List<Issues>>(appExecutors) {

            override fun saveCallResult(response: List<Issues>) =
                issuesDao.insertIssues(response)

            override fun loadFromDb() = issuesDao.loadIssues(repoUrl)

            override  fun createCall() = githubService.getIssues(user, repo)
            override fun shouldFetch(data: List<Issues>?): Boolean {
                return true
            }

        }.asLiveData()
    }
}