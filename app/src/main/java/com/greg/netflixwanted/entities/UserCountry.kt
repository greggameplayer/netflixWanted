package com.greg.netflixwanted.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
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
    @NonNull
    @ColumnInfo(name = "idUser", index = true)
    val idUser: Int,
    @NonNull
    @ColumnInfo(name = "idCountry", index = true)
    val idCountry: Int
)
