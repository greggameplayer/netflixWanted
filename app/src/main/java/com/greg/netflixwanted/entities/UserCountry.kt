package com.greg.netflixwanted.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(primaryKeys = ["idUser", "idCountry"],
    tableName = "UserCountry",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["idUser"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Country::class,
            parentColumns = ["id"],
            childColumns = ["idCountry"],
            onDelete = CASCADE
        )
    ])
data class UserCountry(
    val idUser: Int,
    val idCountry: Int
)