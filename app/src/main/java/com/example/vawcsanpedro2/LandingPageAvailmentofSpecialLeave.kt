package com.example.vawcsanpedro2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import com.example.vawcsanpedro2.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPageAvailmentofSpecialLeave(
    navController: NavController
) {
    val isDarkTheme = isSystemInDarkTheme()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Availment of Special Leave Benefits",
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryPink
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isDarkTheme) Color.Black else White)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Image
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
                Image(
                    painter = painterResource(id = R.drawable.magnacarta),
                    contentDescription = "Availment of Special Leave Benefits",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
            }

            // Title Section
            SpecialLeaveContentSection(
                title = "Availment of Special Leave Benefit",
                content = "Frequently Asked Questions",
                isDarkTheme = isDarkTheme
            )

            // What is the special leave benefit under the Magna Carta of Women?
            SpecialLeaveContentSection(
                title = "What is the special leave benefit under the Magna Carta of Women?",
                content = "The MCW special leave benefit refers to a female employee's leave entitlement of up to two (2) months with full pay based on her gross monthly compensation following surgery caused by gynecological disorders. Gross monthly compensation is defined as monthly basic pay plus mandatory allowances fixed by the law/regional wage board.",
                isDarkTheme = isDarkTheme
            )

            // What are gynecological disorders and what surgeries fall under this category?
            SpecialLeaveContentSection(
                title = "What are gynecological disorders and what surgeries fall under this category?",
                content = "Gynecological disorders are disorders that would require surgical procedures such as, but not limited to, dilation and curettage and those involving female reproductive organs such as the vagina, cervix, uterus, fallopian tubes, ovaries, breast, adnexa and pelvic floor. Gynecological surgeries shall also include myomectomy, hysterectomy, ovariectomy and mastectomy.",
                isDarkTheme = isDarkTheme
            )

            // Who may avail the special leave benefit?
            SpecialLeaveContentSection(
                title = "Who may avail the special leave benefit?",
                content = "Women employees, regardless of age and civil status\n\n" +
                        "In the private sector, any female employee who has been with the company for the last twelve (12) months and has rendered at least six (6) months of continuous aggregate service may avail the said leave prior to undergoing surgery.\n\n" +
                        "In the public sector, any female employee who has rendered at least six (6) months aggregated service in any various government agencies for the last twelve (12) months prior to undergoing surgery for gynecological disorders may avail of the said leave.",
                isDarkTheme = isDarkTheme
            )

            // When should the application for the special leave benefit be filed?
            SpecialLeaveContentSection(
                title = "When should the application for the special leave benefit be filed?",
                content = "The special leave benefit may be filed in advance, at least five (5) days for the government sector, or within a reasonable period of time prior to the scheduled date of gynecological surgery for the private sector. In case of emergency surgical procedure, the said leave shall be filed immediately upon the employee's return from such leave.",
                isDarkTheme = isDarkTheme
            )

            // When can the MCW Special Leave benefit be availed?
            SpecialLeaveContentSection(
                title = "When can the MCW Special Leave benefit be availed?",
                content = "MCW Special Leave benefit may be availed following surgery caused by a gynecological disorder. However, for employees in the private sector, the employer, in its discretion, may allow said employee to receive her pay for the period covered by the approved leave before or during the surgery.",
                isDarkTheme = isDarkTheme
            )

            // What if the employee incurred leave of absence prior to the surgery?
            SpecialLeaveContentSection(
                title = "What if the employee incurred leave of absence prior to the surgery?",
                content = "Female employees in the private sector may charge their leave of absence to the company leave and other mandated leave benefits. On the other hand, female employees in the government sector may use their earned sick leave credits or their vacation leave credits, after their sick leave credits have been exhausted.",
                isDarkTheme = isDarkTheme
            )

            // Can it be availed more than once?
            SpecialLeaveContentSection(
                title = "Can it be availed more than once?",
                content = "Yes. The special leave benefit may be availed for every instance of surgery due to gynecological disorder for a maximum total period of two (2) months or sixty (60) calendar days per year.",
                isDarkTheme = isDarkTheme
            )

            // What if the doctor prescribed more than 60 days of recuperation period?
            SpecialLeaveContentSection(
                title = "What if the doctor prescribed more than 60 days of recuperation period?",
                content = "In case the recuperation period will exceed 60 days, female employees in the private sector may charge their leave of absence to the company leave and other mandated leave benefits. On the other hand, female employees in the government sector may use their earned sick leave credits or their vacation leave credits, after their sick leave credits have been exhausted.",
                isDarkTheme = isDarkTheme
            )

            // Can a female employee who has undergone a surgery due to gynecological disorder during maternity leave avail the special leave benefit?
            SpecialLeaveContentSection(
                title = "Can a female employee who has undergone a surgery due to gynecological disorder during maternity leave avail the special leave benefit?",
                content = "The woman employee who has undergone surgery due to gynecological disorder during her maternity leave is entitled only to the difference between the maternity leave benefits and the special leave benefit under Republic Act No. 9710.",
                isDarkTheme = isDarkTheme
            )

            // How should the employee's salary be computed for the period covered by her approved special leave?
            SpecialLeaveContentSection(
                title = "How should the employee's salary be computed for the period covered by her approved special leave?",
                content = "Section 18 of RA 9710 provides for the entitlement to Special Leave Benefits of women employees who have rendered continuous aggregate employment of at least six (6) months for the last twelve (12) months shall be entitled to a special leave benefit of two (2) months with full pay based on her gross monthly compensation following surgery caused by gynecological disorders;",
                isDarkTheme = isDarkTheme
            )

            // Is Special leave benefit the same as SSS sickness benefit?
            SpecialLeaveContentSection(
                title = "Is Special leave benefit the same as SSS sickness benefit?",
                content = "No. The MCW Special Leave benefit is different from SSS sickness benefit. The MCW Special Leave benefit is granted by the employer to a woman employee who has undergone surgery due to gynecological disorder while the SSS sickness benefit is administered and given by SSS in accordance with SSS Law or RA 1161 as amended by RA 8282.",
                isDarkTheme = isDarkTheme
            )

            // Can the MCW Special Leave benefit be earned and/or converted to cash?
            SpecialLeaveContentSection(
                title = "Can the MCW Special Leave benefit be earned and/or converted to cash?",
                content = "No. MCW Special Leave benefit is non-cumulative and non-convertible to cash unless otherwise provided by a collective bargaining agreement (CBA) in the private sector.",
                isDarkTheme = isDarkTheme
            )

            // What will happen to the existing or similar benefits under a company policy, practice or collective bargaining agreement (CBA)?
            SpecialLeaveContentSection(
                title = "What will happen to the existing or similar benefits under a company policy, practice or collective bargaining agreement (CBA)?",
                content = "The existing similar benefits under a company policy, practice or CBA shall be considered as compliance, unless the company policy, practice or CBA provides otherwise. In case the company policy, practice or CBA provides lesser benefits, the company shall grant the difference.",
                isDarkTheme = isDarkTheme
            )

            // What are the documents needed in filing for the special leave benefit?
            SpecialLeaveContentSection(
                title = "What are the documents needed in filing for the special leave benefit?",
                content = "• Accomplished and approved leave form being used in the agency/company\n" +
                        "• Medical Certificate certified by a competent medical authority preferably specializing in gynecological disorders who is in the position to determine the recuperation period of the woman employee\n" +
                        "• The Medical Certificate shall be accompanied by a clinical summary reflecting the gynecological disorder, histopathological report, operative technique used, duration of surgery, period of confinement, as well as period of recuperation.",
                isDarkTheme = isDarkTheme
            )

            // Contact Information
            SpecialLeaveContentSection(
                title = "For further queries or concerns regarding the special leave benefits, please directly contact the following:",
                content = "For the private sector:\n\n" +
                        "Department of Labor and Employment\n" +
                        "24/7 Hotline: 1349\n" +
                        "E-expensive\n" +
                        "E-Query, click here.\n\n" +
                        "For the government sector:\n\n" +
                        "Civil Service Commission – Human Resource Policies and Standards Office\n" +
                        "Telephone: (02) 8951-4629\n" +
                        "Telefax: (02) 8931-4144\n" +
                        "Text CSC: 0917-839-8272\n" +
                        "Email: hrpso@csc.gov.ph",
                isDarkTheme = isDarkTheme
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SpecialLeaveContentSection(
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
