package com.surajkgoyal.ltgithub.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.surajkgoyal.ltgithub.db.dao.IssuesDao
import com.surajkgoyal.ltgithub.db.dao.ReposDao
import com.surajkgoyal.ltgithub.db.model.Issues
import com.surajkgoyal.ltgithub.db.model.Repos

@Database(entities = [Repos::class, Issues::class], version = 1)
abstract class GithubDatabase: RoomDatabase() {

    abstract fun getReposDao(): ReposDao

    abstract fun getIssuesDao(): IssuesDao
}