package com.example.tomovanguard_tugas.ui.Presentation.Room.Components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tomovanguard_tugas.RoomDB.Data
import com.example.tomovanguard_tugas.RoomDB.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RoomViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    var data by mutableStateOf(Data(0, "", ""))
        private set

    val getAllData = repository.getAllData()

    private var deleteData : Data? = null

    fun insertData(data: Data){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(data)
        }
    }

    fun updateData(data: Data){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(data)
        }
    }

    fun deleteData(data: Data){
        viewModelScope.launch(Dispatchers.IO) {
            deleteData = data
            repository.deleteData(data)
        }
    }

    fun undoDeletedData(){
        deleteData?.let {
            viewModelScope.launch(Dispatchers.IO) {
                repository.insertData(it)
            }
        }
    }
    fun getDataById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            data = repository.getDataById(id)
        }
    }
    fun updateTask(newValue: String){
        data = data.copy(task = newValue)
    }
    fun updateDescription(newValue: String){
        data = data.copy(description = newValue)
    }
}