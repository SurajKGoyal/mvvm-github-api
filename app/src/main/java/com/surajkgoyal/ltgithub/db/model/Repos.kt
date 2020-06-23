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
    var name: String = "",

    var owner: String? = "",

    @SerializedName("description")
    var description: String = "",

    @SerializedName("language")
    var language: String? = "",

    @SerializedName("has_issues")
    var hasIssues: Boolean? = false,

    @SerializedName("forks_count")
    var forksCount: Int? = 0,

    @SerializedName("watchers_count")
    var watchersCount: Int? = 0
) {
}