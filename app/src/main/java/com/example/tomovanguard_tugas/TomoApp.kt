package com.example.tomovanguard_tugas

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tomovanguard_tugas.Retrofit.RetrofitViewModel
import com.example.tomovanguard_tugas.ui.assets.BottomBar.BottomBar
import com.example.tomovanguard_tugas.ui.assets.BottomBar.BottomBarComponents
import com.example.tomovanguard_tugas.ui.Presentation.Login.LoginScreen
import com.example.tomovanguard_tugas.ui.Presentation.Splash.SplashScreen
import com.example.tomovanguard_tugas.Route.Screen
import com.example.tomovanguard_tugas.ui.Presentation.Alarm.AlarmScreen
import com.example.tomovanguard_tugas.ui.Presentation.GoogleMaps.GoogleMapsScreen
import com.example.tomovanguard_tugas.ui.Presentation.Member.Components.Connect
import com.example.tomovanguard_tugas.ui.Presentation.Member.MemberScreen
import com.example.tomovanguard_tugas.ui.Presentation.Register.RegisterScreen
import com.example.tomovanguard_tugas.ui.Presentation.Retrofit.RetrofitScreen
import com.example.tomovanguard_tugas.ui.Presentation.Room.Components.RoomUpdate
import com.example.tomovanguard_tugas.ui.Presentation.Room.Components.RoomViewModel
import com.example.tomovanguard_tugas.ui.Presentation.Room.RoomScreen
import com.example.tomovanguard_tugas.ui.Presentation.Splash.Components.DataSplashScreen
import com.example.tomovanguard_tugas.ui.assets.TopBar.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TomoApp(
    modifier: Modifier = Modifier,
    navController : NavHostController = rememberNavController(),
    viewModel : RoomViewModel = hiltViewModel(),
    RetrofitViewModel : RetrofitViewModel = hiltViewModel()
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            if (currentRoute == Screen.MemberDetail.route + "/{memberId}"){
                TopAppBar(title = {
                    Text(text = "Detail Screen")
                },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = null)
                        }
                    })
            } else {
                AnimatedVisibility(visible = currentRoute.TopBar()) {
                    TopBar(navController = navController)
                }
            }

        },
        bottomBar = {

            AnimatedVisibility(visible = currentRoute.BottomBar()){
                Box(
                    modifier
                        .border(
                            border = BorderStroke(1.5.dp, Color(0xFF246DBB)),
                            shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                        )
                ) {
                    BottomBarComponents(navController = navController)
                }
            }
        }
    ){paddingValues ->
        NavHost(navController = navController,
            startDestination = Screen.Splash.route,
            modifier
                .padding(paddingValues)
        ) {
            composable(Screen.Splash.route){
                SplashScreen(navController = navController)
            }
            composable(Screen.Login.route){
                LoginScreen(navController = navController)
            }
            composable(Screen.Room.route){
                RoomScreen(viewModel = viewModel){id ->
                    navController.navigate(Screen.RoomDetail.route + "/$id")
                }
            }
            composable(Screen.GoogleMaps.route){
                GoogleMapsScreen()
            }
            composable(Screen.Retrofit.route){
                RetrofitScreen(viewModel = RetrofitViewModel)
            }
            composable(Screen.Register.route){
                RegisterScreen(navController = navController)
            }
            composable(Screen.DataSplash.route){
                DataSplashScreen(navController = navController)
            }
            composable(Screen.Alarm.route){
                AlarmScreen(navController = navController)
            }
            composable(Screen.RoomDetail.route + "/{id}",
                arguments = listOf(navArgument("id"){
                    type = NavType.IntType
                })
            ){
                navBackStackEntry?.arguments?.getInt("id").let { id ->
                    RoomUpdate(
                        id = id!!,
                        viewModel = viewModel,
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
            composable(Screen.Member.route){
                MemberScreen(navController = navController)
            }
            composable(
                Screen.MemberDetail.route + "/{memberId}",
                arguments = listOf(navArgument("memberId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                Connect(memberId = navBackStackEntry.arguments?.getInt("memberId"))
            }

        }
    }
}