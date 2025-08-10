package com.example.vawcsanpedro2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
fun LandingPageRA9208(navController: NavController) {
    val isDarkTheme = isSystemInDarkTheme()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "RA 9208 - Anti-Trafficking Act",
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
            .background(if (isDarkTheme) DarkBackground else White)
            .padding(paddingValues)
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            // Header Image
        Image(
            painter = painterResource(id = R.drawable.ra9208),
            contentDescription = "RA 9208 Icon",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp)
        )

            // Title
        Text(
                text = "Republic Act 9208",
                fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
                color = PrimaryPink,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
                text = "The Anti-Trafficking in Persons Act of 2003, as amended by RA 10364",
            fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = TextMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Content Sections
            ContentSection(
                title = "What is Trafficking in Persons (TIP)?",
                content = "Trafficking in persons is an illegal act and is considered a violation of human rights. It has three (3) inter-related and interdependent elements for a situation to be considered trafficking in persons:\n\n1. ACTS – It involves the recruitment, obtaining, hiring, providing, offering, transportation, transfer, maintaining, harboring, or receipt of persons with or without the victim's consent or knowledge, within or across national borders;\n\n2. MEANS – It is committed by use of threat, or use of force, or other forms or coercion, abduction, fraud, deception, abuse of power or of position, taking advantage of the vulnerability of the person, or the giving or receiving of payments or benefits to achieve the consent of a person having control over another person;\n\n3. PURPOSE – It is done for the purpose of exploitation or the prostitution of others or other forms of sexual exploitation, forced labor or services, slavery, involuntary servitude or the removal or sale of organs."
            )

            ContentSection(
                title = "Acts Punishable under the Anti-TIP Law",
                content = """
                    The following acts are punishable under RA 9208 as amended by RA 10364:
                    
                    1. Acts of TIP – includes all acts committed by any natural or juridical person where all the three elements of TIP (acts, means, purpose) are present. Some examples are:
                    • recruitment, hiring, offering, transferring, harboring or receiving any person for the purpose of prostitution, pornography, sexual exploitation, under the pretext of domestic or overseas employment or apprenticeship
                    • undertaking or organizing tours and travel plans consisting of tourism packages or activities for the purpose of utilizing and offering persons for prostitution, pornography or sexual exploitation
                    • maintaining or hiring a person to engage in prostitution or pornography
                    • adopting persons by any form of consideration for exploitative purposes
                    • recruitment, hiring, adopting, transporting, obtaining, harboring, offering, receiving or abducting a person, by means of threat or use of force, fraud, deceit, violence, coercion, or intimidation for the purpose of removal or sale of organs of said person
                    
                    Any person found guilty of acts of TIP shall be penalized with imprisonment of 20 years and a fine of not less than 1 million but not more than 2 million pesos.
                    
                    2. Acts that promote TIP – includes all acts that encourages or facilitates TIP such as:
                    • knowingly using or allowing the use of any house or establishment for promoting TIP
                    • facilitating the use of tampered or fake documents to evade government regulatory and pre-departure requirements
                    • production, publication, broadcast and distribution, including use of ICT for propaganda materials that promote TIP
                    • assisting in the conduct of misrepresentation or fraud in securing clearances and necessary exit documents from government, and facilitating exit/entry of persons possessing tampered or fraudulent travel documents for promoting TIP
                    • preventing a trafficked person from seeking redress from appropriate agencies by confiscating or destroying his/her passport, travel or personal documents
                    • tampering with or destroying of evidence, or influencing or attempting to influence witnesses in an investigation or prosecution of a TIP case
                    • destroying, concealing, removing, confiscating or possessing travel and/or working documents of any person in order to maintain the labor or services of that person
                    • using one's office to impede the investigation, prosecution or execution of lawful orders in a case
                    
                    Any person found guilty of acts that promote TIP shall be penalized with imprisonment of 15 years and a fine of not less than 500 thousand but not more than 1 million pesos.
                    
                    3. Use of Trafficked Persons – any person who buys or engages the services of trafficked persons for prostitution shall be penalized with imprisonment ranging from six (6) years to forty (40) years and fine ranging from 50 thousand to 5 million pesos.
                    
                    4. Qualified TIP – the act will be considered as qualified TIP when:
                    • the trafficked person is a child
                    • there is adoption under RA 8043 (Inter-Country Adoption Act) and said adoption is for the purpose of prostitution, pornography, sexual exploitation, forced labor, slavery, involuntary servitude or debt bondage
                    • the crime is committed by a syndicate or is large scale
                    • the offender is a spouse, ascendant, parent, sibling, guardian or a person who exercises authority over the trafficked person
                    • the offense is committed by a public official or employee
                    • the trafficked person is recruited to engage in prostitution with any member of the military or law enforcement agencies
                    • the offender is a member of the military or law enforcement agencies
                    • the trafficked person died, became insane, suffered mutilation or got infected with HIV/AIDS
                    • the offender commits one or more acts of TIP over a period of 60 or more days
                    • the offender directs or through another manages the trafficking victim
                    
                    Qualified trafficking shall be penalized by life imprisonment and a fine of not less than 2 million but not more than 5 million pesos.
                    
                    5. Attempted TIP – where there are acts to initiate the commission of a trafficking offense but the offender failed to or did not execute all the elements of the crime, by accident or by reason of some cause other than voluntary desistance, such overt acts shall be deemed as an attempt to commit an act of TIP.
                    
                    Any person found guilty of committing attempted TIP shall be penalized with imprisonment of 15 years and a fine of not less than 500 thousand but not more than 1 million pesos.
                """.trimIndent()
            )

            ContentSection(
                title = "Protection for Trafficked Persons",
                content = """
                    Legal Protection:
                    • Free Legal Assistance
                    • Right to Privacy and Confidentiality
                    • Witness Protection Program
                    • Victim Compensation Program
                """.trimIndent()
            )

            ContentSection(
                title = "Who may file a complaint?",
                content = """
                    • The trafficked person or the offended party
                    • Spouse
                    • Parents or legal guardians
                    • Siblings
                    • Children
                    • Any person who has personal knowledge of the offense
                """.trimIndent()
            )

            ContentSection(
                title = "Difference between TIP, Illegal Recruitment, and Human Smuggling",
                content = """
                    Trafficking in Persons:
                    • May or may not involve coercion, fraud, deception, abuse of vulnerability, etc.
                    • Characterized by subsequent exploitation after the illegal entry of one person from one place to another or one country to another
                    • There is a need to prove the presence of exploitation or that the recruitment was facilitated for the purpose of exploitation
                    • Considered a human rights issue
                    
                    Illegal Recruitment:
                    • Usually does not involve coercion but uses more deception, promises and fraud
                    • Characterized by facilitating entry of one person from one country to another through an unorganized or unlicensed agency
                    • Mere recruitment without license is punishable, no need to prove the consequential exploitation
                    • Considered a migration concern
                    
                    Human Smuggling:
                    • Usually does not involve coercion
                    • Characterized by facilitating for a fee, the illegal entry of a person into a foreign country
                    • Proof of illegal entry by non-compliance with the necessary requirements for travel
                    • Considered a migration concern
                """.trimIndent()
            )

        Spacer(modifier = Modifier.height(32.dp))
        }
    }
}