package com.surajkgoyal.ltgithub.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surajkgoyal.ltgithub.db.model.Issues
import com.surajkgoyal.ltgithub.db.model.Repos
import kotlinx.coroutines.flow.Flow

@Dao
interface IssuesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIssues(posts: List<Issues>)

    @Query("UPDATE Issues SET repository=:repo WHERE id=:id")
    fun addRepoName(id: String, repo: String)

    @Query("SELECT * FROM Issues where repository=:repo")
    fun getAllIssues(repo: String): Flow<List<Issues>>
}