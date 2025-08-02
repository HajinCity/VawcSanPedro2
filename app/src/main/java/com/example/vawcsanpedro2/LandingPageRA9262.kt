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
fun LandingPageRA9262(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // RA 9262 Icon
        Image(
            painter = painterResource(id = R.drawable.ra9262),
            contentDescription = "RA 9262 Icon",
            modifier = Modifier
                .size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "RA 9262: the Anti-Violence Against Women and their Children Act of 2004",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Section Title
        Text(
            text = "Frequently Asked Questions",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Question 1
        Text(
            text = "What is RA9262 or the Anti-Violence Against Women and Their Children Act of 2004?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "It is a law that seeks to address the prevalence of violence against women and their children (VAWC) by their intimate partners like their husband or ex-husband, live-in partner or former live-in partner, boyfriend/girlfriend or ex-boyfriend/ex-girlfriend, dating partner or former dating partner.",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Question 2
        Text(
            text = "What is Violence Against Women and Their Children under RA9262?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "It refers to any act or a series of acts committed by an intimate partner (husband, ex-husband, live-in partner, boyfriend/girlfriend, fiancé, who the woman had sexual/dating relationship):",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(12.dp))

        BulletPoint(text = "against a woman who is his wife, former wife;")
        BulletPoint(text = "against a woman with whom the person has or had a sexual or dating relationship;")
        BulletPoint(text = "against a woman with whom he has a common child;")
        BulletPoint(text = "against her child whether legitimate or illegitimate within or without the family abode;")
        BulletPoint(text = "Of which results in or is likely to result in physical, sexual, psychological harm or suffering or economic abuse including threats of such acts, battery, assault, coercion, harassment or arbitrary deprivation of liberty.")

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun BulletPoint(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text("•", fontSize = 16.sp, modifier = Modifier.padding(end = 8.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            textAlign = TextAlign.Start
        )
    }
}