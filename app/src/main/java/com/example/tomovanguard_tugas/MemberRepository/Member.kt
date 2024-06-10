package com.example.tomovanguard_tugas.MemberRepository

data class Member(
    val id: Int,
    val name: String,
    val desc : String,
    val image : Int = 0
){
    fun matchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$name",
            "$desc",
        )
        return matchingCombinations.any{ it.contains(query, ignoreCase = true)}
    }
}
