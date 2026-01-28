package com.example.roomdb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomdb.presentation.screen.addEditUser.AddEditView
import com.example.roomdb.presentation.screen.dashboard.DashboardView
import com.example.roomdb.presentation.screen.dashboard.DashboardViewModel
import com.example.roomdb.presentation.screen.detail.UserDetailView
import com.example.roomdb.presentation.screen.remoteUsers.RemoteUserScreen
import com.example.roomdb.presentation.screen.remoteUsers.RemoteUsersViewModel

@Composable
fun AppNavGraph (
    //viewmodel : DashboardViewModel
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.DASHBOARD
    ){
        composable(Routes.DASHBOARD){
            val viewModel: DashboardViewModel = hiltViewModel()
            DashboardView (
                viewModel = viewModel,
                onAddUserClick = {
                    navController.navigate(route = Routes.ADD_EDIT_USER){
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onUserDetailsClick = { userId ->
                    //viewmodel.selectUser(userId)
                    navController.navigate("${Routes.USER_DETAIL}/$userId"){
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onEditUserClick = { userId ->
                    //viewmodel.selectUser(userId)
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
        ){
            AddEditView (
//                mode = AddEditMode.ADD,
//                viewModel = viewmodel,
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
                //viewModel = viewmodel,
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