package com.surajkgoyal.ltgithub.di

import android.content.Context
import androidx.room.Room
import com.surajkgoyal.ltgithub.db.GithubDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideGithubDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        GithubDatabase::class.java,
        "github_database"
    ).build()

    @Singleton
    @Provides
    fun provideReposDao(db: GithubDatabase) = db.getReposDao()

    @Singleton
    @Provides
    fun provideIssuesDao(db: GithubDatabase) = db.getIssuesDao()

}