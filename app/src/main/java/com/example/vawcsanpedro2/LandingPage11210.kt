package com.example.vawcsanpedro2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.vawcsanpedro2.ui.theme.*

@Composable
fun LandingPage11210(
    navController: NavController
) {
    val scrollState = rememberScrollState()
    val isDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkTheme) DarkBackground else White)
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Section with Title and Image
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isDarkTheme) DarkCard else VeryLightPink
            ),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Availment of the 105-Day Expanded Maternity Leave Under Republic Act 11210 FAQs",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Maternity Leave Image
                Image(
                    painter = painterResource(id = R.drawable.matleave),
                    contentDescription = "Maternity Leave",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
            }
        }

        // What is the Expanded Maternity Leave Card
        InfoCard(
            title = "What is the Expanded Maternity Leave under RA 11210?",
            content = "The 105-Day Expanded Maternity Leave Law (RA 11210 or EML) provides the updated policy on maternity leave that covers females who are workers in the private and public sectors, workers in the informal economy, voluntary contributors to the Social Security System (SSS), and national athletes. It likewise grants a woman the option to allocate up to seven days of paid maternity leave to her child's father or an alternate caregiver.",
            isDarkTheme = isDarkTheme
        )

        // When did the law take effect Card
        InfoCard(
            title = "When did the law take effect and who can benefit from this law?",
            content = "The law took effect on March 11, 2019. Therefore, those who gave birth or had a miscarriage or emergency termination of pregnancy on March 11, 2019 onwards are already covered by RA 11210.",
            isDarkTheme = isDarkTheme
        )

        // Changes in maternity leave period Card
        InfoCard(
            title = "What are the changes in the maternity leave period?",
            content = "The EML increased the paid maternity leave period to 105 days in case of livebirth, with an option to extend it for 30 more days, but without pay. The law also grants an additional 15 days of paid maternity leave for those who qualify as solo parents under RA 8972. These shall apply regardless of the mode of delivery.\n\nIn cases of miscarriage or emergency termination of pregnancy, which includes stillbirth, the paid maternity leave shall be 60 days.",
            isDarkTheme = isDarkTheme
        )

        // Married women question Card
        InfoCard(
            title = "Is the maternity leave applicable only to married women?",
            content = "No, maternity leave can be availed of regardless of the civil status of the woman who gave birth or suffered miscarriage or emergency termination of pregnancy.",
            isDarkTheme = isDarkTheme
        )

        // Consecutive pregnancies Card
        InfoCard(
            title = "What if a woman has consecutive pregnancies or multiple childbirths?",
            content = "A woman may apply for maternity leave in every instance of pregnancy, regardless of frequency.\n\nIn case of overlapping maternity benefit claims (e.g. one miscarriage or emergency termination of pregnancy after another, or a live childbirth followed by miscarriage), she shall be granted maternity leave benefits for the two contingencies in a consecutive manner. For SSS members, the amount of benefit corresponding to the period where there is an overlap shall be deducted from the current maternity benefit claim.\n\nFor multiple childbirths (e.g. twins), a woman shall be granted only one maternity benefit, regardless of the number of offspring/children per delivery.",
            isDarkTheme = isDarkTheme
        )

        // Counting of leave period Card
        InfoCard(
            title = "Does the counting of maternity leave period include Saturdays, Sundays, and Holidays?",
            content = "Yes, maternity leave should be availed of in a continuous and uninterrupted manner, thus, inclusive of Saturdays, Sundays, and Holidays. Maternity Leave is counted in calendar days.",
            isDarkTheme = isDarkTheme
        )

        // Terminated employee Card
        InfoCard(
            title = "Can a female employee who was terminated or who resigned from employment avail of maternity leave?",
            content = "Yes, a female employee can avail of maternity leave if live childbirth, miscarriage, or emergency termination of pregnancy occurs not more than fifteen (15) calendar days after the termination of her employment. Such a period is not applicable when the employment of the pregnant worker has been terminated without cause.",
            isDarkTheme = isDarkTheme
        )

        // Private Sector Guide Header
        SectionHeader(
            title = "Guide for private sector workers, workers in the informal economy, and voluntary contributors to the SSS",
            isDarkTheme = isDarkTheme
        )

        // Eligibility Card
        InfoCard(
            title = "Eligibility",
            content = "To qualify for the grant of maternity leave benefits, a female member must have paid at least three monthly SSS contributions in the 12-month period immediately preceding the semester of the birth, miscarriage, or emergency termination of pregnancy.",
            isDarkTheme = isDarkTheme
        )

        // Notice Requirement Card
        InfoCard(
            title = "Notice Requirement",
            content = "The employee should notify her employer of the pregnancy and the expected date of delivery, who in turn shall transmit it to the SSS. The female worker shall still receive her maternity benefits even if she failed to notify her employer of her pregnancy, but it shall be subject to the guidelines prescribed by the SSS.\n\nSelf-employed, voluntary, and overseas Filipino workers (OFWs) who are members of the SSS may give notice directly to the SSS.",
            isDarkTheme = isDarkTheme
        )

        // Extended Maternity Leave Card
        InfoCard(
            title = "Extended Maternity Leave",
            content = "In the case of live birth, a female worker in the private sector has the option to avail of an additional maternity leave of 30 days without pay. She should submit a written notice to her employer that she is availing of such at least 45 days before the end of her paid maternity leave. In case of a medical emergency, prior notice shall not be necessary, but she should subsequently notify her employer of such.",
            isDarkTheme = isDarkTheme
        )

        // Public Sector Guide Header
        SectionHeader(
            title = "Guide for public sector workers",
            isDarkTheme = isDarkTheme
        )

        // Public Sector Eligibility Card
        InfoCard(
            title = "Eligibility",
            content = "A pregnant female worker in the government service, regardless of employment status and length of service, in National Government Agencies, Local Government Units, Government-Owned or Controlled Corporations, State Universities and Colleges, or Local Universities and Colleges may avail of the maternity leave benefits under RA 11210.",
            isDarkTheme = isDarkTheme
        )

        // Allocation of leave credits Card
        InfoCard(
            title = "What is allocation of maternity leave credits?",
            content = "In the case of live birth, a female worker may allocate or transfer up to seven days of her paid maternity leave to the child's father. This option is not applicable in cases of miscarriage or emergency termination of pregnancy, including stillbirth.\n\nIn the absence of the father, the female employee may still allocate said maternity leave to an alternate caregiver who is either a relative within the 4th degree of consanguinity or a current partner, regardless of sexual orientation or gender identity, who shares with her the same household.\n\nWritten notice to both the mother and the father or alternate caregiver's employers shall be submitted to avail of the benefits.",
            isDarkTheme = isDarkTheme
        )

        // Penalties Card
        InfoCard(
            title = "What are the penalties for the violation of the EML?",
            content = "Employers or agencies that violate RA 11210 shall be fined from P20,000.00 to P200,000.00; or shall be imprisoned for at least 6 years and 1 day or not more than 12 years. Moreover, their business permits shall no longer be renewed.",
            isDarkTheme = isDarkTheme
        )

        // Action Buttons Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
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
                    text = "Need Help?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // File Complaint Button
                Button(
                    onClick = { navController.navigate("complaint_form") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryPink,
                        contentColor = White
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(bottom = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.vawc3),
                        contentDescription = "File Icon",
                        tint = White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("File a Complaint", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                
                // Back to Main Button
                Button(
                    onClick = { navController.navigate("landing") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = InfoBlue,
                        contentColor = White
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.vawc2),
                        contentDescription = "Back Icon",
                        tint = White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Back to Main Menu", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun InfoCard(
    title: String,
    content: String,
    isDarkTheme: Boolean
) {
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
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            Text(
                text = content,
                fontSize = 15.sp,
                textAlign = TextAlign.Justify,
                color = if (isDarkTheme) DarkTextPrimary else TextDark,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    isDarkTheme: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkTheme) DarkSurface else InfoBlue
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (isDarkTheme) DarkTextPrimary else White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}
