package com.example.tomovanguard_tugas.RoomDB

import kotlinx.coroutines.flow.Flow

class Repository(private val dao: Dao) {
    suspend fun insertData(data: Data) = dao.insertData(data)

    suspend fun updateData(data: Data) = dao.updateData(data)

    suspend fun deleteData(data: Data) = dao.deleteData(data)

    suspend fun getDataById(id: Int) = dao.getDataById(id)

    fun getAllData(): Flow<List<Data>> = dao.getAllData()
}