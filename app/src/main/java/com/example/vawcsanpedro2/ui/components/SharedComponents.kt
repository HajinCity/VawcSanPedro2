package com.example.vawcsanpedro2.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vawcsanpedro2.ui.theme.*

@Composable
fun ContentSection(
    title: String,
    content: String,
    isDarkTheme: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkTheme) DarkCard else VeryLightPink
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = content,
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                color = if (isDarkTheme) DarkTextPrimary else TextDark,
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
fun ContentSection(
    title: String,
    content: String
) {
    ContentSection(title = title, content = content, isDarkTheme = false)
}
