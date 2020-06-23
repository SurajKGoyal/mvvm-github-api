package com.surajkgoyal.ltgithub.repositories

import com.surajkgoyal.ltgithub.api.GithubService
import com.surajkgoyal.ltgithub.db.dao.IssuesDao
import com.surajkgoyal.ltgithub.db.model.Issues
import com.surajkgoyal.ltgithub.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class IssuesRepository @Inject constructor(
    val issuesDao: IssuesDao,
    val githubService: GithubService
) {

    /**
     * Fetched the repos from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    fun getAllIssues(repo: String, user:String): Flow<State<List<Issues>>> {
        return object : NetworkBoundRepository<List<Issues>, List<Issues>>() {

            override suspend fun saveRemoteData(response: List<Issues>) =
                issuesDao.insertIssues(response)

            override fun fetchFromLocal(): Flow<List<Issues>> = issuesDao.getAllIssues(repo)

            override suspend fun fetchFromRemote(): Response<List<Issues>> = githubService.getIssues(user, repo)

        }.asFlow().flowOn(Dispatchers.IO)
    }
}