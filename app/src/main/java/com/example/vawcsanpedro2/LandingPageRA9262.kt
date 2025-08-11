package com.example.vawcsanpedro2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vawcsanpedro2.ui.components.ContentSection
import com.example.vawcsanpedro2.ui.theme.DarkBackground
import com.example.vawcsanpedro2.ui.theme.PrimaryPink
import com.example.vawcsanpedro2.ui.theme.TextMedium
import com.example.vawcsanpedro2.ui.theme.White
import com.example.vawcsanpedro2.ui.theme.VeryLightPink
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPageRA9262(navController: NavController) {
    val isDarkTheme = isSystemInDarkTheme()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "RA 9262 - Anti-VAWC Act",
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
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Image
            Image(
                painter = painterResource(id = R.drawable.ra9262),
                contentDescription = "RA 9262 Icon",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp)
            )

            // Title
            Text(
                text = "Republic Act 9262",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryPink,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "The Anti-Violence Against Women and their Children Act of 2004",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDarkTheme) VeryLightPink else TextMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Content Sections
            ContentSection(
                title = "What is RA 9262?",
                content = "It is a law that seeks to address the prevalence of violence against women and their children (VAWC) by their intimate partners like their husband or ex-husband, live-in partner or former live-in partner, boyfriend/girlfriend or ex-boyfriend/ex-girlfriend, dating partner or former dating partner."
            )

            ContentSection(
                title = "What is Violence Against Women and Their Children?",
                content = "It refers to any act or a series of acts committed by an intimate partner (husband, ex-husband, live-in partner, boyfriend/girlfriend, fiance, who the woman had sexual/dating relationship) against a woman who is his wife, former wife; against a woman with whom the person has or had a sexual or dating relationship, against a women with whom he has a common child; against her child whether legitimate or illegitimate within or without the family abode, of which results in or is likely to result in physical, sexual, psychological harm or suffering or economic abuse including threats of such acts, battery, assault, coercion, harassment or arbitrary deprivation of liberty."
            )

            ContentSection(
                title = "Acts of Violence Covered:",
                content = """
                    R.A. 9262 covers several acts of violence:
                    
                    1. Physical Violence – acts that include bodily or physical harm (battery)
                    • causing/threatening/attempting to cause physical harm to the woman or her child
                    • placing the woman or her child in fear of imminent physical harm
                    
                    2. Sexual Violence – the acts which are sexual in nature committed against a woman or her child
                    • Rape, sexual harassment, acts of lasciviousness
                    • treating a woman or her child as a sex object
                    • making demeaning and sexually suggestive remarks
                    • physically attacking the sexual parts of the victim's body
                    • forcing him or her to watch obscene publications and indecent shows
                    • forcing the woman or her child to do indecent acts and/or make films thereof
                    • forcing the wife and mistress/lover to live in the conjugal home or sleep together in the same room with the abuser
                    
                    3. Psychological Violence – Acts or omissions causing or likely to cause mental or emotional suffering
                    • controlling or restricting the woman's or her child's movement or conduct
                    • threatening to or actually depriving the woman or her child of custody or access to her/his family
                    • depriving or threatening to deprive the woman or her child of a legal right
                    • causing mental or emotional anguish, public ridicule or humiliation
                    • threatening or actually inflicting physical harm on oneself for the purpose of controlling the woman's actions or decisions
                    
                    4. Economic Abuse – Acts that make or attempt to make a woman financially dependent upon her abuser
                    • preventing the woman from engaging in any legitimate profession, occupation, business or activity
                    • controlling the woman's own money or property
                    • solely controlling the conjugal or common money/properties
                    • destroying household property
                """.trimIndent()
            )

            ContentSection(
                title = "Who are Protected?",
                content = """
                    The following are the persons who are protected by R.A. 9262:
                    
                    • Wife
                    • Former Wife
                    • A woman with whom the offender has or had sexual relations with
                    • A woman with whom the offender has a common child with
                    • The legitimate or illegitimate child of the woman within or without the family abode
                """.trimIndent()
            )

            ContentSection(
                title = "What can women and children do?",
                content = "Under the law, the offended party may file a criminal action, or apply for a Protection Order either as an independent action or as an incident in civil or criminal action and other remedies."
            )

            ContentSection(
                title = "Who may file a complaint?",
                content = "Any citizen having personal knowledge of the circumstances involving the commission of the crime may file a complaint because violence against women and their children is considered a PUBLIC crime."
            )

            ContentSection(
                title = "Where should cases be filed?",
                content = "Cases may be filed in the Regional Trial Court designated as FAMILY COURT of the place where the crime was committed. These courts have original and exclusive jurisdiction over these cases."
            )

            ContentSection(
                title = "Penalties",
                content = "Offenders proven in court to be guilty of the crime shall be penalized with: imprisonment ranging from 1 month and 1 day to 20 years, payment of P100,000 to P300,000 in damages, mandatory psychological counseling or psychiatric treatment."
            )

            ContentSection(
                title = "Prescriptive Period",
                content = """
                    The criminal complaint may be filed within twenty (20) years from the occurrence or commission for the following acts:
                    
                    • Causing physical harm to the woman or her child
                    • Threatening to cause the woman or her child physical harm
                    • Attempting to cause the woman or her child physical harm
                    • Placing the woman or her child in fear of imminent physical harm
                    
                    The criminal complaint may be filed within ten (10) years from the occurrence or commission for the following acts:
                    
                    • Causing or attempting to cause the woman or her child to engage in any sexual activity which does not constitute rape
                    • Engaging in purposeful, knowing, or reckless conduct that alarms or causes substantial emotional or psychological distress
                """.trimIndent()
            )

            ContentSection(
                title = "Protection Orders",
                content = """
                    A protection order is an order issued under this act for the purpose of preventing further acts of violence against women or her child. And granting other relief as may be needed.
                    
                    Types of Protection Orders:
                    
                    1. Barangay Protection Orders (BPO) - issued by the Punong Barangay, effective for 15 days
                    2. Temporary Protection Orders (TPO) - issued by the court, effective for thirty (30) days
                    3. Permanent Protection Order (PPO) - issued by the court after notice and hearing, effective until revoked
                """.trimIndent()
            )

            ContentSection(
                title = "Where to seek help?",
                content = """
                    • Barangay VAW Desk in your Barangay Hall
                    • Provincial/City/Municipal Social Welfare and Development Office of Local Government Units
                    • Department of Social Welfare and Development (DSWD)
                    • Philippine National Police (PNP) Women and Children Protection Center
                    • Women and Children Protection Desk of nearest Precinct
                    • National Bureau of Investigation (NBI) Anti-Violence Against Women and Children Division (VAWCD)
                    • DOJ – Public Attorney's Office (DOJ-PAO)
                    • UP-PGH Women's Desk
                    • DOH Hospitals, Women and Children Protection Unit
                    • Women's Crisis Center (WCC)
                """.trimIndent()
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}