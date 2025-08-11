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
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.vawcsanpedro2.ui.theme.*
import com.example.vawcsanpedro2.ui.components.*

@Composable
fun LandingPage(
    navController: NavController
) {
    val scrollState = rememberScrollState()
    val isDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkTheme) DarkBackground else White)
            .verticalScroll(scrollState)
            .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Community Image
        Image(
            painter = painterResource(id = R.drawable.sanpedro8),
            contentDescription = "Community Image",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        // Mission Statement Card
        ContentSection(
            title = "Our Mission",
            content = "We, The Barangay San Pedro Pagadian City are here for you. Through comprehensive support systems, legal assistance, counseling services, and public education, the VAWC platform strives to break the cycle of violence and build safer communities. It emphasizes the importance of speaking out, seeking justice, and fostering a culture of respect and equality where every woman and child can live free from fear and harm.",
            isDarkTheme = isDarkTheme,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Silhouettes Illustration
        Image(
            painter = painterResource(id = R.drawable.sanpedro4),
            contentDescription = "Silhouettes",
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        )

        // File a Complaint Button - Enhanced with new component
        PrimaryButton(
            text = "File a Complaint",
            onClick = { navController.navigate("terms") },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(vertical = 24.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Section: FAQ - Enhanced Design
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
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

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Frequently Asked Questions",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = if (isDarkTheme) DarkTextPrimary else TextDark
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

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

        // RA 11210
        IconWithLabel(
            iconRes = R.drawable.ra11210,
            label = "The 105-Day Expanded Maternity Leave Act",
            backgroundColor = BrightYellow,
            onClick = { navController.navigate("11210") },
            isDarkTheme = isDarkTheme
        )

        Spacer(modifier = Modifier.height(32.dp))

        // RA 11313
        IconWithLabel(
            iconRes = R.drawable.ra11313,
            label = "Republic Act No. 11313: Safe Spaces Act (Bawal Bastos Law)",
            backgroundColor = LightPurple,
            onClick = { navController.navigate("ra11313") },
            isDarkTheme = isDarkTheme
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Magna Carta of Women
        IconWithLabel(
            iconRes = R.drawable.magnacarta,
            label = "Magna Carta of Women",
            backgroundColor = Purple,
            onClick = { navController.navigate("magnaCarta") },
            isDarkTheme = isDarkTheme
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Availment of Special Leave Benefit
        IconWithLabel(
            iconRes = R.drawable.leaveform,
            label = "Availment of Special Leave Benefit",
            backgroundColor = BrightYellow,
            onClick = { navController.navigate("availmentSpecialLeave") },
            isDarkTheme = isDarkTheme
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Use of Maiden Name
        IconWithLabel(
            iconRes = R.drawable.useofmaidenname,
            label = "Use of Maiden Name",
            backgroundColor = LightPurple,
            onClick = { navController.navigate("useOfMaidenName") },
            isDarkTheme = isDarkTheme
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Woman and Flowers Illustration
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.sanpedro12),
                contentDescription = "Woman and Flowers",
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Everyone Deserves Kindness,\nCare, and Compassion",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
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
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkTheme) LightPink else White
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(28.dp)
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

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = label,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = if (isDarkTheme) Color.Black else TextDark,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
    }
}