package com.surajkgoyal.ltgithub.di

import com.surajkgoyal.ltgithub.api.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitService(): GithubService = Retrofit.Builder()
        .baseUrl(GithubService.GITHUB_API_URL)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()
        .create(GithubService::class.java)
}