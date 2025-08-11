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
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.vawcsanpedro2.ui.theme.*
import com.example.vawcsanpedro2.ui.components.ContentSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPageMagnaCarta(
    navController: NavController
) {
    val isDarkTheme = isSystemInDarkTheme()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Republic Act 9710 - Magna Carta of Women",
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
                    contentDescription = "Magna Carta of Women",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
            }

            // Title Section
            ContentSection(
                title = "Republic Act 9710 or the Magna Carta of Women",
                content = "Frequently Asked Questions",
                isDarkTheme = isDarkTheme
            )

            // What is Magna Carta of Women?
            ContentSection(
                title = "What is Magna Carta of Women (Republic Act No. 9710)?",
                content = "The Magna Carta of Women (MCW) is a comprehensive women's human rights law that seeks to eliminate discrimination through the recognition, protection, fulfillment, and promotion of the rights of Filipino women, especially those belonging to the marginalized sectors of society.\n\n" +
                        "It is the local translation of the provisions of the Convention on the Elimination of All Forms of Discrimination against Women's (CEDAW), particularly in defining gender discrimination, state obligations, substantive equality, and temporary special measures. It also recognizes human rights guaranteed by the International Covenant on Economic, Social, and Cultural Rights (ICESCR).",
                isDarkTheme = isDarkTheme
            )

            // Why is this law entitled the Magna Carta of Women and not Magna Carta for Women?
            ContentSection(
                title = "Why is this law entitled the Magna Carta of Women and not Magna Carta for Women?",
                content = "In the process of national consultation during the 13th Congress, it was decided to rename the bill to Magna Carta of Women to highlight women's participation and ownership of the bill.",
                isDarkTheme = isDarkTheme
            )

            // What is discrimination against women?
            ContentSection(
                title = "What is discrimination against women?",
                content = "The Magna Carta of Women defines discrimination against women as:\n\n" +
                        "• any gender-based distinction, exclusion, or restriction which has the effect or purpose of impairing or nullifying the recognition, enjoyment, or exercise by women, irrespective of their marital status, on a basis of equality of men and women, of human rights and fundamental freedoms in the political, economic, social, cultural, civil or any other field;\n\n" +
                        "• any act or omission, including by law, policy, administrative measure, or practice, that directly or indirectly excludes or restricts women in the recognition and promotion of their rights and their access to and enjoyment of opportunities, benefits, or privileges;\n\n" +
                        "• a measure or practice of general application that fails to provide for mechanisms to offset or address sex or gender-based disadvantages or limitations of women, as a result of which women are denied or restricted in the recognition and protection of their rights and in their access to and enjoyment of opportunities, benefits, or privileges; or women, more than men are shown to have suffered the greater adverse effects of those measures or practices; and\n\n" +
                        "• discrimination compounded by or intersecting with other grounds, status, or condition, such as ethnicity, age, poverty, or religion.",
                isDarkTheme = isDarkTheme
            )

            // What are the rights of women guaranteed under the Magna Carta of Women?
            ContentSection(
                title = "What are the rights of women guaranteed under the Magna Carta of Women?",
                content = "All rights in the Philippine Constitution and those rights recognized under international instruments duly signed and ratified by the Philippines, in consonance with Philippine laws shall be rights of women under the Magna Carta of Women. These rights shall be enjoyed without discrimination since the law prohibits discrimination against women, whether done by public and private entities or individuals.\n\n" +
                        "The Magna Carta of Women also spells out every woman's right to:\n\n" +
                        "• Protection from all forms of violence, including those committed by the State. This includes the incremental increase in the recruitment and training of women in government services that cater to women victims of gender-related offenses. It also ensures mandatory training on human rights and gender sensitivity to all government personnel involved in the protection and defense of women against gender-based violence, and mandates local government units to establish a Violence Against Women Desk in every barangay to address violence against women cases;\n\n" +
                        "• Protection and security in times of disaster, calamities and other crisis situations, especially in all phases of relief, recovery, rehabilitation and construction efforts, including protection from sexual exploitation and other sexual and gender-based violence;\n\n" +
                        "• Participation and representation, including undertaking temporary special measures and affirmative actions to accelerate and ensure women's equitable participation and representation in the third level civil service, development councils and planning bodies, as well as political parties and international bodies, including the private sector;\n\n" +
                        "• Equal treatment before the law, including the State's review and when necessary amendment or repeal of existing laws that are discriminatory to women;\n\n" +
                        "• Equal access and elimination of discrimination against women in education, scholarships and training. This includes revising educational materials and curricula to remove gender stereotypes and images, and outlawing the expulsion, non-readmission, prohibiting enrollment and other related discrimination against women students and faculty due to pregnancy outside of marriage;\n\n" +
                        "• Equal participation in sports. This includes measures to ensure that gender-based discrimination in competitive and non-competitive sports is removed so that women and girls can benefit from sports development;\n\n" +
                        "• Non-discrimination in employment in the field of military, police and other similar services. This includes the same promotional privileges and opportunities as their men counterpart, including pay increases, additional benefits, and awards, based on competency and quality of performance. The dignity of women in the military, police and other similar services shall always be respected, they shall be accorded with the same capacity as men to act in and enter into contracts, including marriage, as well as be entitled to leave benefits for women such as maternity leave, as provided for in existing laws;\n\n" +
                        "• Non-discriminatory and non-derogatory portrayal of women in media and film to raise the consciousness of the general public in recognizing the dignity of women and the role and contribution of women in family, community, and the society through the strategic use of mass media;\n\n" +
                        "• Comprehensive health services and health information and education covering all stages of a woman's life cycle, and which addresses the major causes of women's mortality and morbidity, including access to among others, maternal care, responsible, ethical, legal, safe and effective methods of family planning, and encouraging healthy lifestyle activities to prevent diseases;\n\n" +
                        "• Leave benefits of two (2) months with full pay based on gross monthly compensation, for women employees who undergo surgery caused by gynecological disorders, provided that they have rendered continuous aggregate employment service of at least six (6) months for the last twelve (12) months;\n\n" +
                        "• Equal rights in all matters relating to marriage and family relations. The State shall ensure the same rights of women and men to: enter into and leave marriages, freely choose a spouse, decide on the number and spacing of their children, enjoy personal rights including the choice of a profession, own, acquire, and administer their property, and acquire, change, or retain their nationality. It also states that the betrothal and marriage of a child shall have no legal effect.",
                isDarkTheme = isDarkTheme
            )

            // Rights of women in marginalized sectors
            ContentSection(
                title = "Rights of women in marginalized sectors",
                content = "The Magna Carta of Women also guarantees the civil, political and economic rights of women in the marginalized sectors, particularly their right to:\n\n" +
                        "• Food security and resources for food production, including equal rights in the titling of the land and issuance of stewardship contracts and patents;\n" +
                        "• Localized, accessible, secure and affordable housing;\n" +
                        "• Employment, livelihood, credit, capital and technology;\n" +
                        "• Skills training, scholarships, especially in research and development aimed towards women friendly farm technology;\n" +
                        "• Representation and participation in policy-making or decision-making bodies in the regional, national, and international levels;\n" +
                        "• Access to information regarding policies on women, including programs, projects and funding outlays that affect them;\n" +
                        "• Social protection;\n" +
                        "• Recognition and preservation of cultural identity and integrity provided that these cultural systems and practices are not discriminatory to women;\n" +
                        "• Inclusion in discussions on peace and development;\n" +
                        "• Services and interventions for women in especially difficult circumstances or WEDC;\n" +
                        "• Protection of girl-children against all forms of discrimination in education, health and nutrition, and skills development; and\n" +
                        "• Protection of women senior citizens.\n\n" +
                        "The Magna Carta of Women defines the marginalized sectors as those who belong to the basic, disadvantaged, or vulnerable groups who are mostly living in poverty and have little or no access to land and other resources, basic social and economic services such as health care, education, water and sanitation, employment and livelihood opportunities, housing security, physical infrastructure and the justice system. These include, but are not limited to women in the following sectors or groups: Small farmers and rural workers, Fisherfolk, Urban poor, Workers in the formal economy, Workers in the informal economy, Migrant workers, Indigenous Peoples, Moro, Children, Senior citizens, Persons with disabilities, and Solo parents.",
                isDarkTheme = isDarkTheme
            )

            // How can Filipino women living abroad benefit from this law?
            ContentSection(
                title = "How can Filipino women living abroad benefit from this law?",
                content = "Statistics show that more and more Filipino women are migrating for overseas employment. In many places, women migrant workers have limited legal protections or access to information about their rights, rendering them vulnerable to gender-specific discrimination, exploitation and abuse.\n\n" +
                        "Section 37 of the Magna Carta of Women mandates the designation of a gender focal point in the consular section of Philippine embassies or consulates. The said officer who shall be trained on Gender and Development shall be primarily responsible in handling gender concerns of women migrant workers, especially those in distress. Other agencies (e.g. the Department of Labor and Employment and the Department of Social Welfare and Development) are also mandated to cooperate in strengthening the Philippine foreign posts' programs for the delivery of services to women migrant workers, consistent with the one-country team approach in Foreign Service.",
                isDarkTheme = isDarkTheme
            )

            // Who will be responsible for implementing the Magna Carta of Women?
            ContentSection(
                title = "Who will be responsible for implementing the Magna Carta of Women?",
                content = "The State, the private sector, society in general, and all individuals shall contribute to the recognition, respect and promotion of the rights of women defined and guaranteed in the Magna Carta of Women. The Philippine Government shall be the primary duty-bearer in implementing the said law. This means that all government offices, including local government units and government-owned and controlled corporations shall be responsible to implement the provisions of Magna Carta of Women that falls within their mandate, particularly those that guarantee rights of women that require specific action from the State.\n\n" +
                        "As the primary duty-bearer, the Government is tasked to:\n\n" +
                        "• refrain from discriminating against women and violating their rights;\n" +
                        "• protect women against discrimination and from violation of their rights by private corporations, entities, and individuals;\n" +
                        "• promote and fulfill the rights of women in all spheres, including their rights to substantive equality and non-discrimination.\n\n" +
                        "The Government shall fulfill these duties through the development and implementation of laws, policies, regulatory instruments, administrative guidelines, and other appropriate measures, including temporary special measures. It shall also establish mechanisms to promote the coherent and integrated implementation of the Magna Carta of Women and other related laws and policies to effectively stop discrimination against Filipino women.",
                isDarkTheme = isDarkTheme
            )

            // What are the specific responsibilities of government under the Magna Carta of Women?
            ContentSection(
                title = "What are the specific responsibilities of government under the Magna Carta of Women?",
                content = "The Magna Carta of Women mandates all government offices, including government-owned and controlled corporations and local government units to adopt gender mainstreaming as a strategy for implementing the law and attaining its objectives. It also mandates (a) planning, budgeting, monitoring and evaluation for gender and development, (b) the creation and/or strengthening of gender and development focal points, and (c) the generation and maintenance of gender statistics and sex-disaggregated databases to aid in planning, programming and policy formulation.\n\n" +
                        "Under this law, the National Commission on the Role of Filipino Women which will be renamed as the Philippine Commission on Women (PCW) shall be the overall monitoring and oversight body to ensure the implementation of the law. As an agency under the Office of the President of the Philippines, it will be the primary policy-making and coordinating body for women and gender equality concerns and shall lead in ensuring that government agencies are capacitated on the effective implementation of the Magna Carta of Women.\n\n" +
                        "Consistent with its mandate, the Commission on Human Rights shall act as the Gender and Development Ombud to ensure the promotion and protection of women's human rights.\n\n" +
                        "The Commission on Audit shall conduct an annual audit on the government offices' use of their gender and development budgets for the purpose of determining its judicious use and the efficiency, and effectiveness of interventions in addressing gender issues.\n\n" +
                        "Local government units are also encouraged to develop and pass a gender and development code to address the issues and concerns of women in their respective localities based on consultation with their women constituents.",
                isDarkTheme = isDarkTheme
            )

            // What are the penalties of violators?
            ContentSection(
                title = "What are the penalties of violators?",
                content = "If the violation is committed by a government agency or any government office, including government-owned and controlled corporations and local government units, the person directly responsible for the violation, as well as the head of the agency or local chief executive shall be held liable under the Magna Carta of Women. The sanctions under administrative law, civil service or other appropriate laws shall be recommended by the Commission on Human Rights to the Civil Service Commission and the Department of the Interior and Local Government.\n\n" +
                        "Further, in cases where violence has been proven to be perpetrated by agents of the State, such shall be considered aggravating offenses with corresponding penalties depending on the severity of the offenses.\n\n" +
                        "If the violation is committed by a private entity or individual, the person directly responsible for the violation shall be liable to pay damages. Further, the offended party can also pursue other remedies available under the law and can invoke any of the other provisions of existing laws, especially those that protect the rights of women.",
                isDarkTheme = isDarkTheme
            )

            // How will the implementation of the Magna Carta of Women be funded?
            ContentSection(
                title = "How will the implementation of the Magna Carta of Women be funded?",
                content = "The Magna Carta of Women provides that the State agencies should utilize their gender and development budgets for programs and activities to implement its provisions. Funds necessary for the implementation of the Magna Carta of Women shall be charged against the current appropriations of the concerned agencies, and shall be included in their annual budgets for the succeeding years. The Magna Carta of Women also mandates the State to prioritize allocation of all available resources to effectively fulfill its obligations under the said law.",
                isDarkTheme = isDarkTheme
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}




