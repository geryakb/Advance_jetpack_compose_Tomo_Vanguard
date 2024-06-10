package com.example.tomovanguard_tugas.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: Data)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateData(data: Data)

    @Delete
    suspend fun deleteData(data: Data)

    @Query("SELECT * FROM data WHERE id = :id")
    suspend fun getDataById(id: Int): Data

    @Query("SELECT * FROM data")
    fun getAllData(): Flow<List<Data>>
}