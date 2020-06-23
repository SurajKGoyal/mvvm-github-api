package com.surajkgoyal.ltgithub.repositories

import com.surajkgoyal.ltgithub.api.GithubService
import com.surajkgoyal.ltgithub.db.model.Repos
import com.surajkgoyal.ltgithub.db.dao.ReposDao
import com.surajkgoyal.ltgithub.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReposRepository @Inject constructor(
    val reposDao: ReposDao,
    val githubService: GithubService
) {

    /**
     * Fetched the repos from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    @ExperimentalCoroutinesApi
    fun getAllRepos(user: String): Flow<State<List<Repos>>> {
        return object : NetworkBoundRepository<List<Repos>, List<Repos>>() {

            override suspend fun saveRemoteData(response: List<Repos>){
                reposDao.insertRepos(response)
            }


            override fun fetchFromLocal(): Flow<List<Repos>> = reposDao.getAllRepos(user)

            override suspend fun fetchFromRemote(): Response<List<Repos>> = githubService.getRepos(user)

        }.asFlow().flowOn(Dispatchers.IO)
    }
}