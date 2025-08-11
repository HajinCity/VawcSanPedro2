package com.example.vawcsanpedro2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import com.example.vawcsanpedro2.ui.components.ContentSection
import com.example.vawcsanpedro2.ui.theme.DarkBackground
import com.example.vawcsanpedro2.ui.theme.DarkCard
import com.example.vawcsanpedro2.ui.theme.DarkPrimaryPink
import com.example.vawcsanpedro2.ui.theme.PrimaryPink
import com.example.vawcsanpedro2.ui.theme.VeryLightPink
import com.example.vawcsanpedro2.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPageRA11313(
    navController: NavController
) {
    val isDarkTheme = isSystemInDarkTheme()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Republic Act No. 11313: Safe Spaces Act (Bawal Bastos Law)",
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
                    painter = painterResource(id = R.drawable.ssp1),
                    contentDescription = "Safe Spaces Act",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
            }

            // What does the Safe Spaces Act cover?
            ContentSection(
                title = "What does the Safe Spaces Act cover?",
                content = "The law covers all forms of gender-based sexual harassment (GBSH) committed in public spaces, educational or training institutions, workplace, as well as online space.",
                isDarkTheme = isDarkTheme
            )

            // Gender-based Streets and Public Spaces Sexual Harassment
            ContentSection(
                title = "Gender-based Streets and Public Spaces Sexual Harassment",
                content = "GBSH in street and public spaces is defined as acts which are committed through any unwanted and uninvited sexual actions or remarks against any person regardless of the motive for committing such action or remarks.",
                isDarkTheme = isDarkTheme
            )

            // What do public spaces refer to under this law?
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isDarkTheme) DarkCard else VeryLightPink
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "What do public spaces refer to under this law?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ssp2),
                        contentDescription = "Public Spaces",
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // What are the acts of gender-based sexual harassment (GBSH) in public spaces?
            ContentSection(
                title = "What are the acts of gender-based sexual harassment (GBSH) in public spaces?",
                content = "a. Catcalling or unwanted remarks directed towards a person, commonly done in the form of wolf-whistling (paninipol), misogynistic, transphobic, homophobic, and sexist slurs, as well as unwanted invitations;\n\n" +
                        "Sexist remarks or slurs-statements that are indicative of prejudice, stereotyping, or discrimination on the basis of sex, typically against women\n\n" +
                        "Homophobic remarks are indicative of fear, hatred or aversion towards persons who are perceived to be or actually identify as lesbian, gay, bisexual, queer, pansexual and such other persons of diverse sexual orientation, gender identity or expression, or towards any person perceived to or actually have experienced same-sex attraction.\n\n" +
                        "Misogynistic remarks or slurs- statements that are indicative of the feeling of hating women or the belief that men are inherently better than women\n\n" +
                        "Transphobic remarks or slurs-statements that are indicative of fear, hatred or aversion towards persons whose gender identity and/or expression do not conform with their sex assigned at birth.\n\n" +
                        "b. Persistent uninvited comments or gestures on a person's appearance;\n\n" +
                        "c. Relentless requests for personal details;\n\n" +
                        "d. Statement of sexual comments and suggestions;\n\n" +
                        "e. Public masturbation or flashing of private parts, groping, making offensive body gestures at someone, and other similar lewd sexual actions;\n\n" +
                        "f. Any advances, whether verbal or physical, that is unwanted and has threatened one's sense of personal space and physical safety. This may include cursing, leering and intrusive gazing, and taunting;\n\n" +
                        "g. Persistent telling of sexual jokes, use of sexual names; and\n\n" +
                        "h. Stalking or conduct directed at a person involving the repeated visual or physical proximity, non-consensual communication, or a combination thereof that cause or will likely cause a person to fear for one's own safety or the safety of others, or to suffer emotional distress.",
                isDarkTheme = isDarkTheme
            )

            // What are the penalties for GBSH in public spaces?
            ContentSection(
                title = "What are the penalties for GBSH in public spaces?",
                content = "Penalties vary according to the act of GBSH committed and how often a person was convicted for violating the laws.\n\n" +
                        "Local government units may come up with ordinances that impose heavier penalties for the acts specified in the Safe Spaces Act, subject to the conditions set under the Administrative Code.",
                isDarkTheme = isDarkTheme
            )

            // Penalties Image
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isDarkTheme) DarkCard else VeryLightPink
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ssp3),
                    contentDescription = "Penalties",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Additional penalties if the perpetrator is the driver or operator of a PUV
            ContentSection(
                title = "Additional penalties if the perpetrator is the driver or operator of a PUV:",
                content = "• Cancellation of license of the driver by the Land Transportation Office (LTO)\n" +
                        "• Suspension or revocation of franchise by the Land Transportation Franchising and Regulatory Board (LTFRB). This sanction may be imposed upon order by the proper court OR upon order of LTO/LTFRB in an administrative proceeding\n\n" +
                        "PUVs include motor vehicles considered as public transport conveyance or common carrier duly registered with the LTO and granted a franchise by the LTFRB including special PUVs such as school services. PUV also includes public water transport utilities and air carrier or operator as registered with and/or regulated by the Maritime Industry Authority, the Civil Aviation Authority of the Philippines, or the Civil Aeronautics Board.",
                isDarkTheme = isDarkTheme
            )

            // What are the roles of Local Government Units in Implementing anti-GBSH in streets and public spaces?
            ContentSection(
                title = "What are the roles of Local Government Units in Implementing anti-GBSH in streets and public spaces?",
                content = "Under the law and its Implementing Rules and Regulations, LGUs shall have the following duties to address GBSH in public spaces:\n\n" +
                        "a. pass an ordinance to localize the law within sixty (60) days from its effectivity;\n" +
                        "b. disseminate or post in conspicuous places a copy of the law and the corresponding ordinance; The LGUs may come up with information, education, and communication (IEC) materials which may be in their respective languages;\n" +
                        "c. provide measures to prevent GBSH in educational institutions, such as information campaigns and anti-sexual harassment seminars;\n" +
                        "d. discourage GBSH and impose fines on acts of gender-based sexual harassment as defined in the law;\n" +
                        "e. establish an anti-sexual harassment hotline where personnel assigned to attend the hotline knowledgeable on GBSH and the forms of assistance made available by the LGU;\n" +
                        "f. coordinate with the DILG in implementing the law.\n" +
                        "g. establish a referral system for complainants of GBSH in streets and public spaces. This may form part of an existing referral system for complainants of other forms of gender-based violence;\n" +
                        "h. provide training on the law for the Punong Barangay and members of the Lupong Tagapamayapa in cases covered by the Katarungang Pambarangay system, for traffic enforcers under their jurisdiction, and adopt training modules for concerned LGU personnel down to the barangay level;\n" +
                        "i. set up Anti-Sexual Harassment (ASH) desks in all barangay, city and municipal halls, preferably staffed by a woman. VAW Desks may also serve as the ASH desks and the same shall be strengthened, following the guidelines to be set by the DILG; and\n" +
                        "j. create a mechanism for handling and documentation of complaints including those in cases covered by the 1st & 2nd offenders of \"Verbal\" GBSH (Implementing Rules and Regulation)",
                isDarkTheme = isDarkTheme
            )

            // Where can victims of GBSH in street and public spaces seek assistance?
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isDarkTheme) DarkCard else VeryLightPink
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Where can victims of GBSH in street and public spaces seek assistance?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ssp4),
                        contentDescription = "Seek Assistance",
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Gender-Based Online Sexual Harassment
            ContentSection(
                title = "Gender-Based Online Sexual Harassment",
                content = "Gender-based Online Sexual Harassment includes acts that use information and communications technology in terrorizing and intimidating victims through:\n\n" +
                        "• threats (physical, psychological, and emotional), unwanted sexual misogynistic, transphobic, homophobic and sexist remarks and comments online whether publicly or through direct and private messages;\n" +
                        "• invasion of the victim's privacy through cyberstalking and incessant messaging;\n" +
                        "• uploading and sharing without the consent of the victim any form of media that contains photos, voice, or video with sexual content;\n" +
                        "• any unauthorized recording and sharing of any of the victim's photos, videos or any information online;\n" +
                        "• impersonating identities of victims online or posting lies about victims to harm their reputation; or\n" +
                        "• filing false abuse reports to online platforms to silence victims.",
                isDarkTheme = isDarkTheme
            )

            // What is cyberstalking?
            ContentSection(
                title = "What is cyberstalking?",
                content = "Cyberstalking is a form by stalking that is committed through an electronic medium in which online communication takes place. To constitute an offense, the conduct must be manifested through the (repeated) use of electronic communications in stalking.\n\n" +
                        "\"Stalking\" an online profile which is publicly accessible in itself does not constitute an offense.",
                isDarkTheme = isDarkTheme
            )

            // What are the penalties for online gender-based sexual harassment?
            ContentSection(
                title = "What are the penalties for online gender-based sexual harassment?",
                content = "The penalty of online GBSH violation is prision correccional in its medium period [imprisonment of six (6) months and one (1) day to two (2) years and four (4) months] or a fine of not less than one hundred thousand pesos (PHP 100,000.00) but not more than five hundred thousand pesos (PHP 500,000.00), or both, at the discretion of the court.\n\n" +
                        "If the perpetrator is a juridical person, its license or franchise shall be automatically deemed revoked, and the persons liable shall be the officers thereof, including the editor or reporter in the case of print media, and the station manager, editor and broadcaster in the case of broadcast media.\n\n" +
                        "An alien (foreigner) who commits gender-based online sexual harassment shall be subjected to deportation proceedings after serving sentence and payment of fines.",
                isDarkTheme = isDarkTheme
            )

            // Where can a victim of online gender-based sexual harassment file a complaint?
            ContentSection(
                title = "Where can a victim of online gender-based sexual harassment file a complaint?",
                content = "The victim can file a complaint directly with the National Bureau of Investigation through its Cybercrime Division, the Philippine National Police Anti-Cybercrime Group, or the Office of the Cybercrime of the Department of Justice.",
                isDarkTheme = isDarkTheme
            )

            // Practical tips in saving digital evidence and online report
            ContentSection(
                title = "Practical tips in saving digital evidence and online report",
                content = "• Take note of the Uniform Resource Locator (URL) or the web address of the website or social media account of the perpetrator\n" +
                        "• Make a printout of the content being complained of by opening a browser (Google Chrome, Mozilla Firefox, etc.) on a laptop or desktop, going to the pertinent account/page, and print the said webpage.\n" +
                        "• Contact authorities via PNP's Anti-Cybercrime Group e-complaint desk at https://acg.pnp.gov.ph/eComplaint/ or through their complaint action centers.",
                isDarkTheme = isDarkTheme
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Gender-Based Sexual Harassment in Workplaces
            ContentSection(
                title = "Gender-Based Sexual Harassment in Workplaces",
                content = "Gender-based sexual harassment in the workplace includes the following:\n\n" +
                        "• an act or series of acts involving any unwelcome sexual advances, requests or demand for sexual favors or any act of sexual nature, whether done verbally, physically or through the use of technology such as text messaging or electronic mail or through any other forms of information and communication systems, that has or could have a detrimental effect on the conditions of an individual's employment or education, job performance or opportunities;\n" +
                        "• a conduct of sexual nature and other conduct based on sex affecting the dignity of a person, which is unwelcome, unreasonable, and offensive to the recipient, whether done verbally, physically or through the use of technology such as text messaging or electronic mail or through any other forms of information and communication systems;\n" +
                        "• a conduct that is unwelcome and pervasive and creates an intimidating, hostile or humiliating environment for the recipient.\n\n" +
                        "Workplaces include all sites, locations, spaces, where work is being undertaken by an employee within or outside the premises of the usual place of business of the employer.",
                isDarkTheme = isDarkTheme
            )

            // Is Sexual Harassment between peers or by a subordinate to a superior now covered under this law?
            ContentSection(
                title = "Is Sexual Harassment between peers or by a subordinate to a superior now covered under this law?",
                content = "Yes. The first law to penalize sexual harassment, Anti-Sexual Harassment Act of 1995 (Republic Act No. 7877) recognized that sexual harassment occurs in work, education, and training environments. However, it requires the existence of authority, influence or moral ascendancy between the offender and the offended party. It did not specifically address the issue of \"hostile environment\" resulting from sexual harassment between peers or co-employees, or those committed against a superior. The Safe Spaces Act addresses these gaps by recognizing that sexual harassment can be committed between peers, or by a subordinate to a superior officer.",
                isDarkTheme = isDarkTheme
            )

            // What can a victim of workplace GBSH do?
            ContentSection(
                title = "What can a victim of workplace GBSH do?",
                content = "The victim can file an administrative complaint with the Committee on Decorum and Investigation and/or file a civil and/or criminal case before the courts.",
                isDarkTheme = isDarkTheme
            )

            // What are the responsibilities of employers under the Safe Spaces Act?
            ContentSection(
                title = "What are the responsibilities of employers under the Safe Spaces Act?",
                content = "Under the Safe Spaces Act, employers must:\n\n" +
                        "• disseminate or post in a conspicuous place a copy of the law to all persons in the workplace;\n" +
                        "• provide measures to prevent GBSH in the workplace, such as the conduct of anti-sexual harassment seminars;\n" +
                        "• create an independent internal mechanism or a committee on decorum and investigation (CODI) to investigate and address complaints of gender-based sexual harassment;\n" +
                        "• develop and disseminate, in consultation with all persons in the workplace, a code of conduct or workplace policy which shall:\n" +
                        "  - Expressly reiterate the prohibition on gender-based sexual harassment;\n" +
                        "  - Describe the procedures of the internal mechanism;\n" +
                        "  - Set administrative penalties.\n\n" +
                        "The Code of Conduct to be developed by the employer may provide for penalties to be imposed on members of the CODI in cases of non-performance or inadequate performance of functions –Sec. 32. Development of Code of Conduct, RA 11313 IRR\n\n" +
                        "The CODI to be established by the employer should:\n\n" +
                        "• adequately represent the management, the employees from the supervisory rank, the rank-and-file employees, and the union/s or employee's association if any;\n" +
                        "• designate a woman as its head and not less than half of its members should be women;\n" +
                        "• be composed of members who should be impartial and not connected or related to the alleged perpetrator;\n" +
                        "• investigate and decide on the complaints within ten (10) days or less upon receipt thereof;\n" +
                        "• observe due process;\n" +
                        "• protect the complainant from retaliation without causing her/him any disadvantage, diminution of benefits or displacement without compromising his/her security of tenure; and\n" +
                        "• guarantee gender-sensitive handling of cases, and confidentiality to the greatest extent possible;",
                isDarkTheme = isDarkTheme
            )

            // What are the penalties for the employers?
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isDarkTheme) DarkCard else VeryLightPink
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ssp5),
                    contentDescription = "Employer Penalties",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Gender-Based Sexual Harassment in Educational and Training Institutions
            ContentSection(
                title = "Gender-Based Sexual Harassment in Educational and Training Institutions",
                content = "The same requirements and corresponding penalties imposable upon employers are also applicable to heads of educational and training institutions. So these heads are also required to disseminate the law, develop their own Code of Conduct and establish and/or update the composition of the CODI. Note that these heads have to harmonize the CODI for their employees and for their students.\n\n" +
                        "In addition to such duties, it also requires them to:\n\n" +
                        "• designate an officer-in-charge to receive complaints regarding violations of the law and forward them to the CODI;\n" +
                        "• impose administrative disciplinary measures for students who commit acts of GBSH against their fellow students or teachers;\n" +
                        "• if a school knows or reasonably should know about acts of gender-based sexual harassment or sexual violence being committed that creates a hostile environment, the school must take immediate action to eliminate the same acts, prevent their recurrence, and address their effects;\n" +
                        "• educate students from the elementary to tertiary level about the provisions of the law and how they can report cases of gender-based streets, public spaces and online sexual harassment committed against them.\n\n" +
                        "School heads and heads of training institutions covered by the Commission on Higher Education (CHED), Department of Education (DepED), and Technical Education and Skills Development Authority (TESDA) shall comply with the standards set by the said agencies.\n\n" +
                        "The CODI to be established by the education and training institution should:\n\n" +
                        "• be composed of representatives from the school administration, the trainers, instructors, professors or coaches and students or trainees, students and parents, as the case may be;\n" +
                        "• designate a woman as its head and not less than half of its members should be women;\n" +
                        "• be composed of members who should be impartial and not connected or related to the alleged perpetrator;\n" +
                        "• investigate and decide on the complaints within ten (10) days or less upon receipt thereof;\n" +
                        "• observe due process;\n" +
                        "• protect the complainant from retaliation without causing her/him any disadvantage, diminution of benefits, or displacement, and without compromising his/her security of tenure; and\n" +
                        "• guarantee gender-sensitive handling of cases, and confidentiality to the greatest extent possible.",
                isDarkTheme = isDarkTheme
            )

            // What are the penalties for GBSH in education and training institutions under SSA?
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isDarkTheme) DarkCard else VeryLightPink
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "What are the penalties for GBSH in education and training institutions under SSA?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ssp6),
                        contentDescription = "Education Penalties",
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                    )
                }
            }

            // Note about minor students
            ContentSection(
                title = "Note:",
                content = "Minor students who are found to commit GBSH shall be held liable for administrative sanctions by the school, as provided in their student handbook.",
                isDarkTheme = isDarkTheme
            )

            // Other Salient Features
            ContentSection(
                title = "Other Salient Features",
                content = "Restraining Order\n\n" +
                        "Where appropriate, the court, even before rendering a final decision, may issue an order directing the perpetrator to stay away from the offended person at a distance specified by the court or to stay away from the residence, school, place of employment, or any specified place frequented by the offended person\n\n" +
                        "Psychological and Counseling Services\n\n" +
                        "A victim of GBSH may avail of appropriate remedies as provided for under the law as well as psychological counseling services with the aid of the LGU and the DSWD (in coordination with the DOH). Any fees to be charged in the course of a victim's availment of such remedies or psychological counseling services and other services in consonance with R.A. No. 11036 or the Philippine Mental Health Law shall be borne by the perpetrator.\n\n" +
                        "LGUs and concerned agencies may partner with private entities in the provision of psychological counseling services and other related processes such as the development of a referral system. In all instances, any fees that may be charged or incurred in the course of the counseling shall be borne by the perpetrator.\n\n" +
                        "Exemptions\n\n" +
                        "Acts that are legitimate expressions of indigenous culture and tradition, as well as breastfeeding in public shall not be penalized under the SSA.\n\n" +
                        "Expressions of indigenous culture and tradition include, among others, the wearing of traditional attires of tribes or clans that may show partial nudity. Provided that, such expressions of indigenous culture and tradition do not discriminate against women, girls, and persons of diverse sexual orientation, gender identity, and expression.\n\n" +
                        "Confidentiality\n\n" +
                        "The RA 11313 requires that at any stage of the investigation, prosecution, and trial of an offense under RA 11313, the rights of victim and the accused who is a minor shall be recognized.\n" +
                        "The law requires that confidentiality should be observed at all times by employers and heads of schools and training institutions in complaints to GBSH in their institutions.",
                isDarkTheme = isDarkTheme
            )

            // WHERE TO SEEK HELP?
            ContentSection(
                title = "WHERE TO SEEK HELP?",
                content = "Public Attorney's Office\n" +
                        "Email: pao_executive@yahoo.com\n" +
                        "Telephone numbers:\n" +
                        "(02) 8929-9436\n" +
                        "(02) 8426-2075\n" +
                        "(02) 8426-2801\n" +
                        "(02) 8426-2450\n" +
                        "(02) 8426-2987\n" +
                        "(02) 8426-2683 local 106/107 (Office Hours), local 159 (Outside Office hours)\n\n" +
                        "PNP Women and Children Protection Center\n" +
                        "Aleng Pulis Hotline: 0919-7777377\n" +
                        "Telephone number: (02) 8723-0401 local 5261\n\n" +
                        "For Online GBSH\n\n" +
                        "Cybercrime Investigation and Coordinating Center (CICC)\n" +
                        "Submit a Cyber Complaint\n\n" +
                        "PNP Women and Children Cyber Protection Unit (WCCPU)\n" +
                        "Telephone numbers:\n" +
                        "(02) 8723-0401 local 5354\n" +
                        "0927 084 3792\n" +
                        "Email: pnpacg.wccpu@gmail.com\n\n" +
                        "Office of the Cybercrime (OOC)\n" +
                        "3rd floor De Las Alas Bldg.\n" +
                        "Department of Justice\n" +
                        "P. Faura St., Ermita, Manila\n" +
                        "Email: cybercrime@doj.gov.ph\n" +
                        "Telephone numbers: (02) 8526-2747 and (02) 8521-8345\n\n" +
                        "Under SSA, the following agencies are mandated to conduct routine inspections in relation to GBSH in the workplace:\n\n" +
                        "Department of Labor and Employment\n" +
                        "24/7 Hotline: 1349\n\n" +
                        "Civil Service Commission\n" +
                        "Telephone numbers:\n" +
                        "(02) 8931-8092\n" +
                        "8931-7939\n" +
                        "8931-7935",
                isDarkTheme = isDarkTheme
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}



