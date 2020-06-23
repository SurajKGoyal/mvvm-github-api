package com.surajkgoyal.ltgithub.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surajkgoyal.ltgithub.db.model.Repos
import kotlinx.coroutines.flow.Flow

@Dao
interface ReposDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(repos: List<Repos>)

    @Query("UPDATE Repos SET owner=:user WHERE id=:id")
    fun addOwnerName(id: String, user: String)

    @Query("SELECT * FROM Repos where owner=:user")
    fun getAllRepos(user: String): Flow<List<Repos>>
}