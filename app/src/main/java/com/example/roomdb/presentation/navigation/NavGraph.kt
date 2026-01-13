package com.example.roomdb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomdb.presentation.screen.addEditUser.AddUserScreen
import com.example.roomdb.presentation.screen.addEditUser.EditUserScreen
import com.example.roomdb.presentation.screen.dashboard.DashboardScreen
import com.example.roomdb.presentation.screen.dashboard.DashboardViewModel
import com.example.roomdb.presentation.screen.detail.UserDetailScreen

@Composable
fun AppNavGraph (
    viewmodel : DashboardViewModel
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
                    viewmodel.selectUser(userId)
                    navController.navigate(Routes.USER_DETAIL)
                },
                onEditUserClick = { userId ->
                    viewmodel.selectUser(userId)
                    navController.navigate(Routes.EDIT_USER)
                }
            )
        }
        composable(Routes.ADD_USER){
            AddUserScreen(
                viewModel = viewmodel,
                onBack = { navController.popBackStack() },
            )
        }
        composable(Routes.USER_DETAIL) {
            UserDetailScreen(
                viewModel = viewmodel,
                onBack = {navController.popBackStack()}
            )
        }
        composable(Routes.EDIT_USER) {
            EditUserScreen(
                viewModel = viewmodel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}