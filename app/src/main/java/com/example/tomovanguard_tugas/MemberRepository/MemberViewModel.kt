package com.example.tomovanguard_tugas.MemberRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tomovanguard_tugas.MemberRepository.MemberData.MemberList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class MemberViewModel: ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _person = MutableStateFlow(MemberList)
    val person = searchText
        .combine(_person){ text, person ->
            if (text.isBlank()){
                person
            } else {
                person.filter {
                    it.matchQuery(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _person.value
        )
    fun onSearchTextChange(text : String){
        _searchText.value = text
    }
}