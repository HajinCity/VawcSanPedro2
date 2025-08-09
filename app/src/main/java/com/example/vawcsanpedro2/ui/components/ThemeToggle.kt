package com.example.vawcsanpedro2.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vawcsanpedro2.ui.theme.*

@Composable
fun ThemeToggle(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkTheme) DarkCard else VeryLightPink
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isDarkTheme) "Dark Theme" else "Light Theme",
                color = if (isDarkTheme) DarkTextPrimary else TextDark,
                style = MaterialTheme.typography.bodyLarge
            )
            
            IconButton(
                onClick = { onThemeChange(!isDarkTheme) }
            ) {
                Icon(
                    imageVector = if (isDarkTheme) Icons.Filled.Check else Icons.Filled.Close,
                    contentDescription = if (isDarkTheme) "Switch to Light Theme" else "Switch to Dark Theme",
                    tint = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
