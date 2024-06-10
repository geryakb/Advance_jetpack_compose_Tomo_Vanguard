package com.example.tomovanguard_tugas.ui.assets.BottomBar

import com.example.tomovanguard_tugas.Route.Screen

fun String?.BottomBar(): Boolean {
    return this in setOf(
        Screen.Room.route,
        Screen.GoogleMaps.route,
        Screen.Retrofit.route,
        Screen.Alarm.route,
        Screen.Member.route
    )
}