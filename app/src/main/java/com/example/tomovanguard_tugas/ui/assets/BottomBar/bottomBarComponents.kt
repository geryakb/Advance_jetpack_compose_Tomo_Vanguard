package com.example.tomovanguard_tugas.ui.assets.BottomBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tomovanguard_tugas.R
import com.example.tomovanguard_tugas.Route.Screen

@Composable
fun BottomBarComponents(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    NavigationBar{
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val bottomBarItems = listOf(
            bottomBar(
                title ="Room",
                icon = painterResource(id = R.drawable.icon_room),
                screen = Screen.Room
            ),
            bottomBar(
                title = "Maps",
                icon = painterResource(id = R.drawable.icon_gmaps),
                screen = Screen.GoogleMaps
            ),
            bottomBar(
                title = "List",
                icon = painterResource(id = R.drawable.icon_list),
                screen = Screen.Retrofit
            ),
            bottomBar(
                title = "Alarm",
                icon = painterResource(id = R.drawable.alarm_icon),
                screen = Screen.Alarm
            ),
            bottomBar(
                title = "Anggota",
                icon = painterResource(id = R.drawable.aqua_icon),
                screen = Screen.Member
            )
        )

        bottomBarItems.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(MaterialTheme.colorScheme.onBackground),
                selected = currentRoute == item.screen.route,
                onClick = {
                          navController.navigate(item.screen.route){
                              popUpTo(navController.graph.findStartDestination().id){
                                  inclusive = true
                              }
                              restoreState = true
                              launchSingleTop = true
                          }
                },
                icon = {
                    Image(painter = item.icon,
                        contentDescription = item.title,
                        modifier
                            .size(25.dp)
                    )
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
    }
}