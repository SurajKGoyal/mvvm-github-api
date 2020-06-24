package com.surajkgoyal.ltgithub.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surajkgoyal.ltgithub.db.model.Repos

@Dao
interface ReposDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(repos: List<Repos>)

    @Query(
        """
        SELECT * FROM Repos
        WHERE owner_login = :owner"""
    )
    abstract fun loadRepositories(owner: String): LiveData<List<Repos>>


}