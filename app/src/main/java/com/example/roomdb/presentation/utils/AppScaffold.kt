package com.example.roomdb.presentation.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Reusable Scaffold For Screens.
@Composable
fun AppScaffold(
//    modifier: Modifier = Modifier,
    snackbarHost: @Composable (() -> Unit)? = null,
    topBarContent: @Composable () -> Unit,
    floatingActionButton: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            topBarContent()
        },
        floatingActionButton = {
            floatingActionButton?.invoke()
        },
        snackbarHost = {
            snackbarHost?.invoke()
        },
//        modifier = modifier,
    ) { padding ->
        content(padding)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    subtitle: String? = null,
    icon: (@Composable () -> Unit)? = null,
//    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    Box(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .fillMaxWidth()
            .clipToBounds() // This clips the top shadow
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-2).dp) // Move card up to hide top shadow
                .shadow(
                    elevation = 4.dp,
                    shape = RectangleShape,
                    clip = false
                ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            shape = RectangleShape,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                // Title + Subtitle column
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                    )
                    // Subtitle only if present
                    subtitle?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                }
                // Icon only if present
                icon?.let {
                    it()
                }
            }
        }
    }
}


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    subtitle: String? = null,
    icon: (@Composable () -> Unit)? = null,
//    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    Card(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars) // instead of safeDrawingPadding(). to leave space of status bar
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RectangleShape,
                clip = false
            )
            .graphicsLayer {
                clip = true
//                shape = RectangleShape
                translationY = 8f
//                shadowElevation = 10.dp.toPx()
//                ambientShadowColor = Color.Black
//                spotShadowColor = Color.Black
            }
            .padding(bottom = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp, pressedElevation = 0.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {

            // Title + Subtitle column
            Column(
                modifier = Modifier
                    .weight(1f) //  keeps layout stable when icon exists or not
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )

                // Subtitle only if present
                subtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            // Icon only if present
            icon?.let {
                it()
            }
        }
    }
}
 */