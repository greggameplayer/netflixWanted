package com.greg.netflixwanted.entities

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
    val idUser: Int,
    val idCategory: String
)