package com.example.roomdb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomdb.presentation.screen.addEditUser.AddUserScreen
import com.example.roomdb.presentation.screen.addEditUser.EditUserScreen
import com.example.roomdb.presentation.screen.dashboard.DashboardScreen
import com.example.roomdb.presentation.screen.dashboard.DashboardViewModel
import com.example.roomdb.presentation.screen.detail.UserDetailScreen
import com.example.roomdb.presentation.screen.remoteUsers.RemoteUserScreen
import com.example.roomdb.presentation.screen.remoteUsers.RemoteUsersViewModel

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
                    navController.navigate(route = Routes.ADD_USER){
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onUserClick = { userId ->
                    viewmodel.selectUser(userId)
                    navController.navigate(Routes.USER_DETAIL){
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onEditUserClick = { userId ->
                    viewmodel.selectUser(userId)
                    navController.navigate(Routes.EDIT_USER){
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
        composable(Routes.ADD_USER){
            AddUserScreen(
                viewModel = viewmodel,
                onBack = {
                    navController.popBackStack(
                        route = Routes.DASHBOARD,
                        inclusive = false
                    )
                },
            )
        }
        composable(Routes.USER_DETAIL) {
            UserDetailScreen(
                viewModel = viewmodel,
                onBack = {
                    navController.popBackStack(
                        route = Routes.DASHBOARD,
                        inclusive = false
                    )
                }
            )
        }
        composable(Routes.EDIT_USER) {
            EditUserScreen(
                viewModel = viewmodel,
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

            val viewModel: RemoteUsersViewModel = hiltViewModel()
            RemoteUserScreen(viewModel = viewModel)

        }
    }
}