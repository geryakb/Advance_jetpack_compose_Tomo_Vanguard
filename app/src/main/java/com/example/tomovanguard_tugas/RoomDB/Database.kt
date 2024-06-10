package com.example.tomovanguard_tugas.RoomDB

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Data::class], version = 1, exportSchema = true)
abstract class MyDatabase: RoomDatabase() {
    abstract fun dao    (): Dao
}