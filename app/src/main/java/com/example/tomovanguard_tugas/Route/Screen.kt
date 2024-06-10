package com.example.tomovanguard_tugas.Route

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object DataSplash : Screen("datasplash")
    object Login : Screen("login")
    object Room : Screen("room")
    object GoogleMaps : Screen("googlemaps")
    object Retrofit : Screen("retrofit")
    object Register : Screen("register")
    object Alarm : Screen("alarm")
    object RoomDetail : Screen("roomdetail")
    object Member : Screen("member")
    object MemberDetail : Screen("MemberDetail")
}