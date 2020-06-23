package com.surajkgoyal.ltgithub.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Repos(
    @PrimaryKey
    @SerializedName("id")
    var id: String = "",

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("login")
    var owner: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("language")
    var language: String? = null,

    @SerializedName("has_issues")
    var hasIssues: Boolean? = null,

    @SerializedName("forks_count")
    var forksCount: Int? = null,

    @SerializedName("watchers_count")
    var watchersCount: Int? = null
) {
}