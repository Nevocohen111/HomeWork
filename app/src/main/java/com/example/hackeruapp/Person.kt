package com.example.hackeruapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "peopleTable")
data class Person(
    @ColumnInfo(name = "person_name") val name: String,
    @ColumnInfo(name = "person_image") val image: Int,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}