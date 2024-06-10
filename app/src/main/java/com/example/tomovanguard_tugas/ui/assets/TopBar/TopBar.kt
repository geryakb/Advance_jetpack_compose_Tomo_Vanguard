package com.example.tomovanguard_tugas.ui.assets.TopBar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tomovanguard_tugas.DataStore.DataStore
import com.example.tomovanguard_tugas.Route.Screen
import com.example.tomovanguard_tugas.SharedPreferences.SharedPreferencesManager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navController: NavController
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val appBarTitle = when(currentRoute) {
        Screen.Room.route -> "Room Screen"
        Screen.GoogleMaps.route -> "Google Maps Screen"
        Screen.Retrofit.route -> "Retrofit Screen"
        Screen.Alarm.route -> "Alarm Screen"
        Screen.Member.route -> "Anggota Screen"
        Screen.MemberDetail.route + "/{memberId}" -> "Detail Anggota Screen"
        else -> "Unknown"
    }
    val context = LocalContext.current
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val dataStore = DataStore(context)
    val coroutine = rememberCoroutineScope()
    TopAppBar(title = {
        Text(text = appBarTitle, fontSize = 18.sp)
    },
        navigationIcon = {

        },
        actions = {
            IconButton(onClick = {
                sharedPreferencesManager.clear()
                coroutine.launch {
                    dataStore.clearStatus()
                }
                navController.navigate(Screen.Login.route)
            }) {
                Icon(imageVector = Icons.Default.ExitToApp,
                    contentDescription = null
                )
            }
        }
    )
}