package com.surajkgoyal.ltgithub.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Issues(
    @PrimaryKey
    @SerializedName("id")
    var id: String = "",

    var repository: String? = null,

    @SerializedName("title")
    var title: String? = null
) {
}