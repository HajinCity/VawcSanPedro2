package com.example.vawcsanpedro2

import androidx.compose.foundation.Image
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

@Composable
fun LandingPage(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Community Image
        Image(
            painter = painterResource(id = R.drawable.sanpedro8), // Replace with new image
            contentDescription = "Community Image",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mission Statement
        Text(
            text = "We, The Barangay San Pedro  Pagadian City are here for you. Through comprehensive support systems, legal assistance, counseling services, and public education, the VAWC platform strives to break the cycle of violence and build safer communities. It emphasizes the importance of speaking out, seeking justice, and fostering a culture of respect and equality where every woman and child can live free from fear and harm.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Silhouettes Illustration
        Image(
            painter = painterResource(id = R.drawable.sanpedro4),
            contentDescription = "Silhouettes",
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // File a Complaint Button
        Button(
            onClick = { navController.navigate("terms") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFB6C1),
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.vawc3),
                contentDescription = "File Icon",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("File a Complaint", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(50.dp))

        // Section: FAQ
        IconWithLabel(
            iconRes = R.drawable.faqicon,
            label = "Frequently Asked Questions",
            backgroundColor = Color(0xFF03A9F4),
            onClick = {}
        )

        Spacer(modifier = Modifier.height(70.dp))

        // RA 9262
        IconWithLabel(
            iconRes = R.drawable.ra9262,
            label = "The Anti-Violence Against Women and their Children Act",
            backgroundColor = Color(0xFFE53935),
            onClick = { navController.navigate("ra9262") }
        )

        Spacer(modifier = Modifier.height(70.dp))

        // RA 9208
        IconWithLabel(
            iconRes = R.drawable.ra9208,
            label = "The Expanded Anti-Trafficking in Persons Act",
            backgroundColor = Color(0xFF03A9F4),
            onClick = { navController.navigate("ra9208") }
        )

        Spacer(modifier = Modifier.height(70.dp))

        // RA 7877
        IconWithLabel(
            iconRes = R.drawable.ra7877,
            label = "The Anti-Sexual Harassment Act",
            backgroundColor = Color(0xFFFF9800),
            onClick = { navController.navigate("ra7877") }
        )

        Spacer(modifier = Modifier.height(70.dp))

        // Woman and Flowers Illustration
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
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
@Composable
fun IconWithLabel(
    iconRes: Int,
    label: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
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

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = label,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}