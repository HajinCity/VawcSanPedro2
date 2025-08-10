package com.example.vawcsanpedro2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TermsAndConditionsScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val hasScrolledToBottom = remember {
        derivedStateOf {
            scrollState.value == scrollState.maxValue
        }
    }
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Terms & Conditions",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = List(16) {
                    "We, The Barangay San Pedro Pagadian City are here for you. " +
                            "Through comprehensive support systems, legal assistance, counseling services, " +
                            "and public education, the VAWC platform strives to break the cycle of violence " +
                            "and build safer communities. It emphasizes the importance of speaking out, " +
                            "seeking justice, and fostering a culture of respect and equality where every woman " +
                            "and child can live free from fear and harm.\n"
                }.joinToString("\n"),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        if (hasScrolledToBottom.value) {
                            isChecked = it
                        }
                    },
                    enabled = hasScrolledToBottom.value
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "I have read and accept the Terms & Conditions",
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (isChecked) {
                        navController.navigate("complaint_form")
                    }
                },
                enabled = isChecked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFB6C1),
                    contentColor = Color.Black,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Continue")
            }
        }
    }
}