package com.example.design.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    errorTitle: String,
    errorMessage: String,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Oops! Something went wrong.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.error
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                ),
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onRetryClick,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Retry",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}