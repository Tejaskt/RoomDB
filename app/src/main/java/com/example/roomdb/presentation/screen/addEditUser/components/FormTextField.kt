package com.example.roomdb.presentation.screen.addEditUser.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun FormTextField(
    label: String,
    value: String,
    //placeholder: String,
    error: String? = null,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            //placeholder = { Text(placeholder) },
            isError = error != null,
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxWidth()
        )

        error?.let {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = it, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
