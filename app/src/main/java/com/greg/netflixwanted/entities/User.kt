package com.greg.netflixwanted.entities

import androidx.annotation.NonNull
import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "User")
data class User(@PrimaryKey (autoGenerate = true) @NonNull @field:SerializedName("user_id") val userId: Int?,
                @field:SerializedName("birthday") val birthday: String?)
