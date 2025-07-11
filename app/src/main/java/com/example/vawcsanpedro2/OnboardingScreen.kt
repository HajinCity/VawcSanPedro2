package com.example.vawcsanpedro2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch



@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> OnboardingPage(
                    imageRes = R.drawable.sanpedro9,
                    title = "Welcome",
                    description = "We, The Barangay San Pedro Pagadian City are here for you. Violence against women and children (VAWC) should never be ignored, and no one deserves to suffer in silence. If you or someone you know is experiencing abuse, know that help and support are available."
                )
                // placeholders for upcoming pages
                1 -> OnboardingPage(
                    imageRes = R.drawable.vawc, // change to page 2 image
                    title = "Speak Up",
                    description = "Speak up, reach out, and remember—there is hope, there is healing, and you are never alone. You deserve to be heard. You deserve to be safe. And most importantly, you deserve to be free."
                )
                2 -> OnboardingPage(
                    imageRes = R.drawable.vawc2, // change to page 3 image
                    title = "End Violence",
                    description = "You have the strength within you to rise above the pain, to reclaim your voice, and to build a life free from fear. Together, we can break the cycle, seek justice, and create a safer, brighter future. "
                )
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(16.dp),
            activeColor = Color.Black,
            inactiveColor = Color.Gray
        )

        if (pagerState.currentPage == 2) {
            Button(
                onClick = { onFinish() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFB6C1),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Next", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun OnboardingPage(imageRes: Int, title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
                .padding(bottom = 24.dp)
        )
        Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}
