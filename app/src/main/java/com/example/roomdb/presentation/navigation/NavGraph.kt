package com.example.roomdb.presentation.navigation

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomdb.presentation.screen.addUser.AddUserScreen
import com.example.roomdb.presentation.screen.dashboard.DashboardScreen
import com.example.roomdb.presentation.screen.detail.UserDetailScreen
import com.example.roomdb.presentation.screen.home.HomeViewModel

@Composable
fun AppNavGraph (
    viewmodel : HomeViewModel
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.DASHBOARD
    ){
        composable(Routes.DASHBOARD){
            DashboardScreen(
                viewModel = viewmodel,
                onAddClick = {
                    navController.navigate(Routes.ADD_USER)
                },
                onUserClick = { userId ->
                    navController.navigate("${Routes.USER_DETAIL}/$userId")
                }
            )
        }
        composable(Routes.ADD_USER){
            AddUserScreen(
                viewModel = viewmodel,
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "${Routes.USER_DETAIL}/{userId}",
            arguments = listOf(navArgument("userId"){
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId")!!
            UserDetailScreen(
                viewModel = viewmodel,
                userId = userId,
                navController
            )
        }
    }
}