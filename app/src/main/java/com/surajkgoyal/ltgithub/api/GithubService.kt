package com.surajkgoyal.ltgithub.api


import com.surajkgoyal.ltgithub.db.model.Issues
import com.surajkgoyal.ltgithub.db.model.Repos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/users/{user}/repos")
    fun getRepos(@Path("user")user: String) : Response<List<Repos>>

    @GET("/repos/{user}/{repo}/issues")
    suspend fun getIssues(@Path("user")user: String, @Path("repo")repo:String ) : Response<List<Issues>>

    companion object {
        const val GITHUB_API_URL = "https://api.github.com/"
    }
}