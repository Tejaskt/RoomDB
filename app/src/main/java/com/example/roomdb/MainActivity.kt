package com.example.roomdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.roomdb.data.local.AppDatabase
import com.example.roomdb.data.repository.UserRepository
import com.example.roomdb.presentation.screen.home.HomeScreen
import com.example.roomdb.presentation.screen.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomdb.presentation.screen.home.HomeViewModelFactory
import com.example.roomdb.ui.theme.RoomDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)
        val repository = UserRepository(database.userDao())
        val factory = HomeViewModelFactory(repository)

        setContent {
            RoomDBTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: HomeViewModel = viewModel(
                        modelClass = HomeViewModel::class.java,
                        factory = factory
                    )

                    HomeScreen(viewModel)
                }
            }
        }
    }
}

