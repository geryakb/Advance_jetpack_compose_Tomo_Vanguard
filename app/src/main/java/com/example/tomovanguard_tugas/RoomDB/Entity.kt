package com.example.tomovanguard_tugas.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Data(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val task: String,
    val description: String
)
