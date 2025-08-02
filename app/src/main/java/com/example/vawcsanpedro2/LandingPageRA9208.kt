package com.example.vawcsanpedro2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LandingPageRA9208(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ra9208),
            contentDescription = "RA 9208 Icon",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "RA 9208: Expanded Anti-Trafficking in Persons Act of 2012",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Frequently Asked Questions",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "What is RA 9208?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "RA 9208 seeks to eliminate human trafficking, especially women and children, and provide measures to support victims and prosecute offenders. It includes recruitment, transportation, transfer, harboring, or receipt of persons by means of threat, use of force, coercion, abduction, fraud, deception, or abuse of power.",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Acts considered as trafficking under RA 9208 include:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        BulletPoint("Recruiting persons for prostitution or forced labor")
        BulletPoint("Transporting victims using deception or coercion")
        BulletPoint("Receiving or harboring trafficked individuals")
        BulletPoint("Exploiting a position of power or vulnerability")
        BulletPoint("Trafficking minors even without force or deceit")

        Spacer(modifier = Modifier.height(32.dp))
    }
}