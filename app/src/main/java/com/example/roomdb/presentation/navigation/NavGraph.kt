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
import com.example.roomdb.data.local.entity.User
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


    /* --------- LOGIN BYPASS LOGIC ---------- */

    LaunchedEffect(splashState) {
        when (splashState) {
            SplashState.Authenticated -> {
                navController.navigate(Routes.DASHBOARD) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            }
            SplashState.Unauthenticated -> {
                navController.navigate(Routes.AUTH) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            }
            SplashState.Loading -> Unit
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        /* ---------- SPLASH SCREEN ---------- */
        composable(Routes.SPLASH) {
            SplashScreen()
        }

        /* ---------- AUTH SCREEN ---------- */
        composable(Routes.AUTH) {
            AuthView(
                onNavigateToDashboard = {
                    navController.navigate(Routes.DASHBOARD) {
                        popUpTo(Routes.AUTH) { inclusive = true }
                    }
                }
            )
        }

        /* ---------- DASHBOARD SCREEN ---------- */
        composable(Routes.DASHBOARD) {
            DashboardView(
                onAddUserClick = {
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.remove<User>("user")
                    navController.navigate(Routes.ADD_EDIT_USER)
                },
                onUserDetailsClick = { userId ->
                    navController.navigate("${Routes.USER_DETAIL}/$userId")
                },
                onEditUserClick = { user ->
                    //navController.navigate("${Routes.ADD_EDIT_USER}?userId=$userId")

                    // set user
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("user",user)
                    navController.navigate(Routes.ADD_EDIT_USER)

                },
                onRemoteUsersClick = {
                    navController.navigate(Routes.REMOTE_USERS)
                }
            )
        }

        /* ---------- ADD-EDIT USER SCREEN ---------- */
        composable(Routes.ADD_EDIT_USER) {

            // get user
            val user = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<User>("user")

            AddEditView(
                user = user,
                onBack = {
                    navController.popBackStack(Routes.DASHBOARD, false)
                }
            )
        }

        /* ---------- USER DETAILS SCREEN ---------- */
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

        /* ---------- FETCH API USER SCREEN ---------- */
        composable(Routes.REMOTE_USERS) {
            RemoteUserScreen()
        }
    }
}

