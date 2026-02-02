package com.example.roomdb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomdb.presentation.screen.addEditUser.AddEditView
import com.example.roomdb.presentation.screen.auth.AuthView
import com.example.roomdb.presentation.screen.dashboard.DashboardView
import com.example.roomdb.presentation.screen.userDetails.UserDetailView
import com.example.roomdb.presentation.screen.remoteUsers.RemoteUserScreen
import com.example.roomdb.presentation.screen.splashScreen.SplashScreen
import com.example.roomdb.presentation.screen.splashScreen.SplashViewModel
import com.example.roomdb.presentation.screen.splashScreen.component.SplashState

@Composable
fun AppNavGraph(
    splashVM: SplashViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val splashState by splashVM.splashState.collectAsState()

    LaunchedEffect(splashState) {
        when (splashState) {
            SplashState.Authenticated -> {
                if (navController.currentDestination?.route == Routes.AUTH) {
                    navController.navigate(Routes.DASHBOARD) {
                        popUpTo(Routes.AUTH) { inclusive = true }
                    }
                }
            }

            SplashState.Unauthenticated -> {
                if (navController.currentDestination?.route != Routes.AUTH) {
                    navController.navigate(Routes.AUTH) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }

            SplashState.Loading -> Unit
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.SPLASH) {
            SplashScreen()
        }

        composable(Routes.AUTH) {
            AuthView(
                onNavigateToDashboard = {
                    navController.navigate(Routes.DASHBOARD) {
                        popUpTo(Routes.AUTH) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.DASHBOARD) {
            DashboardView(
                onAddUserClick = {
                    navController.navigate(Routes.ADD_EDIT_USER)
                },
                onUserDetailsClick = { userId ->
                    navController.navigate("${Routes.USER_DETAIL}/$userId")
                },
                onEditUserClick = { userId ->
                    navController.navigate("${Routes.ADD_EDIT_USER}?userId=$userId")
                },
                onRemoteUsersClick = {
                    navController.navigate(Routes.REMOTE_USERS)
                }
            )
        }

        composable(
            route = "${Routes.ADD_EDIT_USER}?userId={userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditView(
                onBack = {
                    navController.popBackStack(Routes.DASHBOARD, false)
                }
            )
        }

        composable(
            route = "${Routes.USER_DETAIL}/{userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.IntType
                }
            )
        ) {
            UserDetailView(
                onBack = {
                    navController.popBackStack(Routes.DASHBOARD, false)
                }
            )
        }

        composable(Routes.REMOTE_USERS) {
            RemoteUserScreen()
        }
    }
}

