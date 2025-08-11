package com.example.vawcsanpedro2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vawcsanpedro2.ui.components.ContentSection
import com.example.vawcsanpedro2.ui.theme.*

@Composable
fun LandingPageUseOfMaidenName(navController: NavController) {
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isDarkTheme) Color.Black else White
            )
            .verticalScroll(rememberScrollState())
    ) {
        // Header with back button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (isDarkTheme) DarkPrimaryPink else PrimaryPink
                )
                .padding(16.dp)
        ) {
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            
            Text(
                text = "Use of Maiden Name",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.Bold
            )
        }

        // Content
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Hero Image
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isDarkTheme) DarkCard else VeryLightPink
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.useofmaidenname),
                        contentDescription = "Use of Maiden Name",
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "Use of Maiden Name",
                        style = MaterialTheme.typography.headlineSmall,
                        color = if (isDarkTheme) Color.White else PrimaryPink,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Frequently Asked Questions
            ContentSection(
                title = "Frequently Asked Questions",
                content = "Common questions and answers about using your maiden name after marriage.",
                isDarkTheme = isDarkTheme
            )

            // Question 1: Are married women obliged to use their husbands' surname?
            ContentSection(
                title = "Are married women obliged or required to use their husbands' surname?",
                content = "No. According to prevailing jurisprudence, \"a married woman has the option, but not a duty, to use the surname of the husband.\" Therefore, upon marriage, married women have the option to continuously use her maiden name or:\n\n• Her maiden first name and surname and add her husband's surname; or\n• Her maiden first name and her husband's surname; or\n• Her husband's full name, but prefixing a word indicating that she is his wife, such as \"Mrs.\"",
                isDarkTheme = isDarkTheme
            )

            // Question 2: Can I use my maiden name again?
            ContentSection(
                title = "I have already used my husband's surname in my identification cards and other documents. Can I use my maiden name again?",
                content = "While there is no blanket prohibition on reverting to one's maiden name, certain special laws limit the instances in which a married woman can resume or revert to her maiden name, such as Republic Act No. 8239 or the Philippine Passport Act of 1996.\n\nThe Philippine Passport Act does not prohibit married women from continuously using their maiden names in their passports. However, once a married woman has opted to adopt her husband's surname in her passport, she may not revert to her maiden name, except in any of the following instances:\n\n• Death of husband;\n• Divorce;\n• Annulment; or\n• Nullity of Marriage.",
                isDarkTheme = isDarkTheme
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

