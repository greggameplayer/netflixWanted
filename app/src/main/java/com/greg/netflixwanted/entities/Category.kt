package com.greg.netflixwanted.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Category")
class Category(@PrimaryKey @NonNull @field:SerializedName("category_id") val categoryId: String,
                    @field:SerializedName("name") val name: String)