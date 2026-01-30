package com.example.roomdb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomdb.presentation.screen.addEditUser.AddEditView
import com.example.roomdb.presentation.screen.auth.AuthView
import com.example.roomdb.presentation.screen.auth.AuthViewModel
import com.example.roomdb.presentation.screen.dashboard.DashboardView
import com.example.roomdb.presentation.screen.userDetails.UserDetailView
import com.example.roomdb.presentation.screen.remoteUsers.RemoteUserScreen
import com.example.roomdb.presentation.screen.splashScreen.SplashScreen
import com.example.roomdb.presentation.screen.splashScreen.SplashViewModel
import com.example.roomdb.presentation.screen.splashScreen.component.SplashEvent

@Composable
fun AppNavGraph (
    splashVM : SplashViewModel = hiltViewModel()
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ){
        composable(Routes.SPLASH){
            SplashScreen()

            LaunchedEffect(Unit) {
                splashVM.eventFlow.collect{ event ->
                    when(event){
                        SplashEvent.NavigateToAuth -> {
                            navController.navigate(Routes.AUTH) {
                                popUpTo(Routes.SPLASH) { inclusive = true }
                            }
                        }
                        SplashEvent.NavigateToDashboard -> {
                            navController.navigate(Routes.DASHBOARD) {
                                popUpTo(Routes.SPLASH) { inclusive = true }
                            }
                        }
                    }
                }
            }
        }

        composable(Routes.AUTH){
            AuthView()
        }

        composable(Routes.DASHBOARD){
            DashboardView (
                onAddUserClick = {
                    navController.navigate(route = Routes.ADD_EDIT_USER){
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onUserDetailsClick = { userId ->
                    navController.navigate("${Routes.USER_DETAIL}/$userId"){
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onEditUserClick = { userId ->
                    navController.navigate("${Routes.ADD_EDIT_USER}?userId=$userId"){
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onRemoteUsersClick = {
                    navController.navigate(Routes.REMOTE_USERS){
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(
            route = "${Routes.ADD_EDIT_USER}?userId={userId}",
            arguments = listOf(
                navArgument("userId"){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditView (
                onBack = {
                    navController.popBackStack(
                        route = Routes.DASHBOARD,
                        inclusive = false
                    )
                },
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
            UserDetailView (
                onBack = {
                    navController.popBackStack(
                        route = Routes.DASHBOARD,
                        inclusive = false
                    )
                }
            )
        }

        composable(Routes.REMOTE_USERS){
            /* with out hilt boilerplate code
            val context = LocalContext.current
            val database = remember {
                AppDatabase.getDatabase(context)
            }

            val repository = remember {
                RemoteUserRepositoryImpl(
                    api = RetrofitClient.api,
                    dao = database.remoteUserDao()
                )
            }

            val factory = remember {
                RemoteUsersViewModelFactory(repository)
            }

            val viewModel: RemoteUsersViewModel = viewModel(factory = factory)

            */
            RemoteUserScreen()
        }
    }
}