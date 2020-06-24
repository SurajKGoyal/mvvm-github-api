package com.surajkgoyal.ltgithub.api


import androidx.lifecycle.LiveData
import com.surajkgoyal.ltgithub.db.model.Issues
import com.surajkgoyal.ltgithub.db.model.Repos
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/users/{login}/repos")
    fun getRepos(@Path("login") login: String): LiveData<ApiResponse<List<Repos>>>

    @GET("/repos/{login}/{repo}/issues")
    fun getIssues(
        @Path("login") login: String,
        @Path("repo") repo: String
    ): LiveData<ApiResponse<List<Issues>>>

    companion object {
        const val GITHUB_API_URL = "https://api.github.com/"
    }
}