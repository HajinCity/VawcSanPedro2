package com.example.vawcsanpedro2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vawcsanpedro2.ui.theme.*
import com.example.vawcsanpedro2.ui.components.ThemeToggle

@Composable
fun LandingPage(
    navController: NavController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkTheme) DarkBackground else White)
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Theme Toggle
        ThemeToggle(
            isDarkTheme = isDarkTheme,
            onThemeChange = onThemeChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        // Top Community Image
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isDarkTheme) DarkCard else VeryLightPink
            ),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.sanpedro8),
                contentDescription = "Community Image",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
        }

        // Mission Statement Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
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
                Text(
                    text = "Our Mission",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )
                Text(
                    text = "We, The Barangay San Pedro Pagadian City are here for you. Through comprehensive support systems, legal assistance, counseling services, and public education, the VAWC platform strives to break the cycle of violence and build safer communities. It emphasizes the importance of speaking out, seeking justice, and fostering a culture of respect and equality where every woman and child can live free from fear and harm.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = if (isDarkTheme) DarkTextPrimary else TextDark,
                    lineHeight = 24.sp
                )
            }
        }

        // Silhouettes Illustration
        Image(
            painter = painterResource(id = R.drawable.sanpedro4),
            contentDescription = "Silhouettes",
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        // File a Complaint Button - Made Bigger
        Button(
            onClick = { navController.navigate("terms") },
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryPink,
                contentColor = White
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(vertical = 20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.vawc3),
                contentDescription = "File Icon",
                tint = White,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text("File a Complaint", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Section: FAQ - Removed Card, Made Simple Header
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Surface(
                color = InfoBlue,
                shape = RoundedCornerShape(100),
                modifier = Modifier.size(80.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.faqicon),
                    contentDescription = "FAQ Icon",
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize()
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = "Frequently Asked Questions",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = if (isDarkTheme) DarkTextPrimary else TextDark
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // RA 9262
        IconWithLabel(
            iconRes = R.drawable.ra9262,
            label = "The Anti-Violence Against Women and their Children Act",
            backgroundColor = ErrorRed,
            onClick = { navController.navigate("ra9262") },
            isDarkTheme = isDarkTheme
        )

        Spacer(modifier = Modifier.height(32.dp))

        // RA 9208
        IconWithLabel(
            iconRes = R.drawable.ra9208,
            label = "The Expanded Anti-Trafficking in Persons Act",
            backgroundColor = InfoBlue,
            onClick = { navController.navigate("ra9208") },
            isDarkTheme = isDarkTheme
        )

        Spacer(modifier = Modifier.height(32.dp))

        // RA 7877
        IconWithLabel(
            iconRes = R.drawable.ra7877,
            label = "The Anti-Sexual Harassment Act",
            backgroundColor = WarningOrange,
            onClick = { navController.navigate("ra7877") },
            isDarkTheme = isDarkTheme
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Woman and Flowers Illustration
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
                    painter = painterResource(id = R.drawable.sanpedro12),
                    contentDescription = "Woman and Flowers",
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Everyone Deserves Kindness,\nCare, and Compassion",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun IconWithLabel(
    iconRes: Int,
    label: String,
    backgroundColor: Color,
    onClick: () -> Unit,
    isDarkTheme: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkTheme) DarkCard else VeryLightPink
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            // Make the icon surface clickable
            Surface(
                color = backgroundColor,
                shape = RoundedCornerShape(100),
                modifier = Modifier
                    .size(100.dp)
                    .clickable { onClick() }
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = label,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = label,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = if (isDarkTheme) DarkTextPrimary else TextDark,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
    }
}