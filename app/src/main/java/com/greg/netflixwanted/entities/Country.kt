package com.greg.netflixwanted.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
class Country (@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int, @ColumnInfo(name = "name") val name: String, @ColumnInfo(name = "countrycode") val countrycode: String)
