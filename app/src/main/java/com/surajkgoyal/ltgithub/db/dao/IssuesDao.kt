package com.surajkgoyal.ltgithub.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surajkgoyal.ltgithub.db.model.Issues

@Dao
interface IssuesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIssues(issues: List<Issues>)


    @Query(
        """
        SELECT * FROM Issues
        WHERE repoUrl = :repo"""
    )
    abstract fun loadIssues(repo: String): LiveData<List<Issues>>

}