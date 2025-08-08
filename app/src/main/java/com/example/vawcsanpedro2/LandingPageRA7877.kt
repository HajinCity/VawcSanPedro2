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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vawcsanpedro2.ui.theme.*
import com.example.vawcsanpedro2.ui.components.ContentSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPageRA7877(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "RA 7877 - Anti-Sexual Harassment Act",
                        color = White,
                        fontWeight = FontWeight.Bold
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
                .background(White)
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Image
            Image(
                painter = painterResource(id = R.drawable.ra7877),
                contentDescription = "RA 7877 Icon",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp)
            )

            // Title
            Text(
                text = "Republic Act 7877",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryPink,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "The Anti-Sexual Harassment Act of 1995",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = TextMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Content Sections
            ContentSection(
                title = "What is Sexual Harassment?",
                content = "Under RA 7877, work, education, or training related sexual harassment is \"committed by an employer, employee, manager, supervisor, agent of the employer, teacher, instructor, professor, coach, trainor, or any person who, having authority, influence or moral ascendancy over another in a work or training or education environment, demands, requests or otherwise requires any sexual favor from the other, regardless of whether the demand, request or requirement for submission is accepted or not by the object of said act.\""
            )

            ContentSection(
                title = "How is work-related sexual harassment committed?",
                content = """
                    Work-related Sexual Harassment is committed when:
                    
                    • The sexual favor is made as a condition in the hiring or in the employment, re-employment or continued employment of said individual, or in granting said individual favorable compensation, terms, conditions, promotions, or privileges; or refusal to grant the sexual favor results in limiting, segregating or classifying the employee which in any way would discriminate, deprive or diminish employment opportunities or otherwise adversely affect said employee
                    • The above acts would impair the employee's rights or privileges under existing labor laws; or
                    • The above acts would result in an intimidating, hostile or offensive environment for the employee
                """.trimIndent()
            )

            ContentSection(
                title = "How is education or training-related sexual harassment committed?",
                content = """
                    Education or Training-related Sexual Harassment is committed:
                    
                    • Against one who is under the care, custody or supervision of the offender
                    • Against one whose education, training, apprenticeship, or tutorship is entrusted to the offender
                    • When the sexual favor is made a condition to the giving of a passing grade, or granting of honors and scholarships, or the payment of a stipend, allowance or other benefits, privileges, or considerations; or
                    • When the sexual advances result in an intimidating, hostile or offensive environment for the student, trainee or apprentice
                """.trimIndent()
            )

            ContentSection(
                title = "Liability for Sexual Harassment",
                content = "A person who directs or induces another person to commit any act of sexual harassment or who cooperates to commit the act, without which the said act would not have been committed, will also be held liable under the law."
            )

            ContentSection(
                title = "Duty of the employer or head of office",
                content = """
                    The employer or head of office is required by the law to prevent the occurrence of sexual harassment acts and to provide the procedures for the resolution, settlement or prosecution of sexual harassment. Towards this end, the employer or head of office shall:
                    
                    • Promulgate appropriate rules and regulations in consultation with and jointly approved by the employees or student or trainees, through their duly designated representatives. Said rules and regulations shall prescribe the procedures for the investigation of sexual harassment cases and the administrative sanctions thereof.
                    
                    • The said rules and regulations should include guidelines on proper decorum in the workplace and educational or training institutions.
                    
                    • Administrative sanctions shall not be a bar to prosecution in the proper courts for unlawful acts of sexual harassment.
                    
                    • Create a Committee on Decorum and Investigation (CODI) of cases on sexual harassment. The committee shall conduct meetings or as the case may be, with officers and employees, teachers, instructors, professors, coaches, trainors and students or trainees to increase understanding and prevent incidents of sexual harassment. It shall also conduct the investigation of alleged cases constituting sexual harassment.
                    
                    • In the case of work-related environment, the committee shall be composed of at least one (1) representative each from the management, the union, if any, the employees from the supervisory rank, and from the rank and file employees.
                    
                    • In the case of the educational or training institution, the committee shall be composed of at least one (1) representative from the administration, the trainors, teachers, instructors, professors or coaches and students and trainees, as the case may be.
                    
                    • The employer or head of office, educational or training institution shall disseminate or post a copy of RA 7877 for the information of all concerned.
                """.trimIndent()
            )

            ContentSection(
                title = "What if the employer or head of office did not undertake any action?",
                content = "The employer or head of office, educational or training institution will be held liable for the damages arising from acts of sexual harassment if they are informed by the offended party of the occurrence of such acts, yet no action has been undertaken."
            )

            ContentSection(
                title = "Can an offended party seek redress by taking an independent action?",
                content = "Yes, the offended party may take independent action for damages incurred in the act of sexual harassment. She/he may also avail of relief."
            )

            ContentSection(
                title = "Penalties for offenders",
                content = """
                    Any person who violates the provisions of the law shall be penalized by imprisonment of not less than one (1) month nor more than six (6) months, or a fine of not less than Ten Thousand Pesos (P10,000) nor more than Twenty Thousand Pesos (P20,000), or both such fine and imprisonment at the discretion of the court.
                    
                    Any act arising from the violation of the provisions of this Act shall prescribe in three (3) years.
                """.trimIndent()
            )

            ContentSection(
                title = "Important Notes",
                content = """
                    • Sexual harassment can occur in both work and educational environments
                    • The law protects both men and women from sexual harassment
                    • Employers and educational institutions have a legal duty to prevent sexual harassment
                    • Victims have the right to file complaints and seek damages
                    • The law provides for both administrative and criminal penalties
                    • Prevention and education are key components of the law
                """.trimIndent()
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}