package com.surajkgoyal.ltgithub.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    indices = [
        Index("id"),
        Index("owner_login")],
    primaryKeys = ["name", "owner_login"]
)
data class Repos(
    @SerializedName("id")
    var id: String = "",

    @field:SerializedName("name")
    var name: String = "",

    @field:SerializedName("full_name")
    var fullName: String ="",

    @field:SerializedName("owner")
    @field:Embedded(prefix = "owner_")
    val owner: Owner,

    @SerializedName("url")
    var url: String? = "",

    @SerializedName("description")
    var description: String? = "",

    @SerializedName("language")
    var language: String? = "",

    @SerializedName("has_issues")
    var hasIssues: Boolean? = false,

    @SerializedName("forks_count")
    var forksCount: Int? = 0,

    @SerializedName("watchers_count")
    var watchersCount: Int? = 0
) {

    data class Owner(
        @field:SerializedName("login")
        val login: String,
        @field:SerializedName("url")
        val url: String?
    )


}