package com.greg.netflixwanted.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(primaryKeys = ["idUser", "idCategory"],
    tableName = "UserCategory",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["idUser"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["categoryId"],
            childColumns = ["idCategory"],
            onDelete = CASCADE
        )
    ])
data class UserCategory(
    @NonNull
    @ColumnInfo(name = "idUser", index = true)
    val idUser: Int,
    @NonNull
    @ColumnInfo(name = "idCategory", index = true)
    val idCategory: String
)
