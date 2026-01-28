package com.example.roomdb

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.roomdb.presentation.navigation.AppNavGraph
import com.example.roomdb.ui.theme.RoomDBTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        /* removed manual creation of instance because of hilt
        val database = AppDatabase.getDatabase(this)
        val repository = UserRepository(database.userDao())
        val factory = DashboardViewModelFactory(repository)
         */

        @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
        setContent {
            RoomDBTheme {
//                Scaffold(
//                    modifier = Modifier
//                        .fillMaxSize()
//                ) { _ ->
                    AppNavGraph()
//                }
            }
        }
    }
}


