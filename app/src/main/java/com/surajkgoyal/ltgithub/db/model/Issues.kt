package com.surajkgoyal.ltgithub.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    primaryKeys = ["id"]
)
data class Issues(

    @SerializedName("id")
    var id: String = "",

    @SerializedName("repository_url")
    var repoUrl: String? = "",

    @field:SerializedName("title")
    var title: String? = ""
) {
}