package com.example.vawcsanpedro2

import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vawcsanpedro2.backendmodel.*
import com.google.firebase.firestore.FirebaseFirestore
import com.example.vawcsanpedro2.backendmodel.EnhancedEncryptionTransit.encryptEnhanced
import com.example.vawcsanpedro2.backendmodel.EnhancedEncryptionTransit.validateComplainantData
import com.example.vawcsanpedro2.backendmodel.EnhancedEncryptionTransit.validateRespondentData
import com.example.vawcsanpedro2.backendmodel.EnhancedEncryptionTransit.validateCaseData
import com.example.vawcsanpedro2.backendmodel.SecurityManager.generateSecureId
import com.example.vawcsanpedro2.ui.theme.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplaintFormScreen(navController: NavHostController) {
    val isDarkTheme = isSystemInDarkTheme()
    val db = FirebaseFirestore.getInstance()
    val lastSubmittedSuffix = remember { mutableStateOf("") }
    val showErrorDialog = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf("") }

    val manilaTimeZone = TimeZone.getTimeZone("Asia/Manila")
    val calendar = Calendar.getInstance(manilaTimeZone)

    val fullDateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    fullDateTimeFormat.timeZone = manilaTimeZone
    val todayFull = fullDateTimeFormat.format(calendar.time)

    val dateOnlyFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    dateOnlyFormat.timeZone = manilaTimeZone
    val todayDateOnly = dateOnlyFormat.format(calendar.time)

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val purokOptions = listOf(
        "Purok Adelfa",
        "Purok Bougainvillea",
        "Purok Campo Islam",
        "Purok Fisherville",
        "Purok Golden Shower",
        "Purok Kabingaan",
        "Purok Lacturan",
        "Purok Masanagon 1",
        "Purok Masanagon 2",
        "Purok Narra",
        "Purok Palmera",
        "Purok Rosas",
        "Purok Sampaguita",
        "Purok Sunshine"
    )

    val sexOptions = listOf("Male", "Female")
    val civilStatusOptions = listOf("Single", "Live-in", "Separated", "Married", "Widowed")
    val relationshipOptions = listOf(
        "Current Spouse/Partner", "Former Fiance/Dating Relationship", "Teacher/Instructor/Professor",
        "Neighbors/Peers/Co-Workers/Classmates", "Former Spouse/Partner", "Employer/Manager/Supervisor",
        "Coach/Trainer", "Stranger", "Current Fiance/Dating Relationship", "Agent of the Employer",
        "People of Authority/Service Provider", "Family", "Other Relatives"
    )
    val incidentPlaceOptions = listOf(
        "Home", "Religious Institutions", "Brothels and Similar Establishments", "Work",
        "Place of Medical Treatment", "School", "Transportation & Connecting Sites",
        "Commercial Places", "No Response", "Others"
    )

    var complainant by remember { mutableStateOf(ComplainantDetails()) }
    var respondent by remember { mutableStateOf(RespondentDetails()) }
    var caseDetails by remember {
        mutableStateOf(CaseDetails(complaintDate = todayFull, incidentDate = todayDateOnly))
    }
    var complaintText by remember { mutableStateOf(TextFieldValue()) }
    val scrollState = rememberScrollState()
    val showSuccessDialog = remember { mutableStateOf(false) }
    val navigateToLandingPage = remember { mutableStateOf(false) }

    if (navigateToLandingPage.value) {
        LaunchedEffect(Unit) {
            navController.navigate("landing") {
                popUpTo("complaint_form") { inclusive = true }
            }
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    "File Your Complaint",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader("Complainant Personal Information", isDarkTheme)
            FormField("Last Name", complainant.lastName, { complainant = complainant.copy(lastName = it) }, isDarkTheme)
            FormField("First Name", complainant.firstName, { complainant = complainant.copy(firstName = it) }, isDarkTheme)
            FormField("Middle Name", complainant.middleName, { complainant = complainant.copy(middleName = it) }, isDarkTheme)
            DropdownField("Sex", complainant.sexIdentification, sexOptions, { complainant = complainant.copy(sexIdentification = it) }, isDarkTheme)
            FormField("Age", complainant.age, { complainant = complainant.copy(age = it) }, isDarkTheme)
            DateField("Birthdate", complainant.birthdate, { complainant = complainant.copy(birthdate = it) }, isDarkTheme)
            DropdownField("Civil Status", complainant.civilStatus, civilStatusOptions, { complainant = complainant.copy(civilStatus = it) }, isDarkTheme)
            FormField("Religion", complainant.religion, { complainant = complainant.copy(religion = it) }, isDarkTheme)
            FormField("Nationality", complainant.nationality, { complainant = complainant.copy(nationality = it) }, isDarkTheme)
            FormField("Occupation", complainant.occupation, { complainant = complainant.copy(occupation = it) }, isDarkTheme)

            SectionHeader("Complainant Contact Information", isDarkTheme)
            FormField("Contact No.", complainant.cellNumber, { complainant = complainant.copy(cellNumber = it) }, isDarkTheme)
            DropdownField("Purok", complainant.address.purok, purokOptions, { complainant = complainant.copy(address = complainant.address.copy(purok = it)) }, isDarkTheme)

            SectionHeader("Respondent Personal Information", isDarkTheme)
            FormField("Last Name", respondent.lastName, { respondent = respondent.copy(lastName = it) }, isDarkTheme)
            FormField("First Name", respondent.firstName, { respondent = respondent.copy(firstName = it) }, isDarkTheme)
            FormField("Middle Name", respondent.middleName, { respondent = respondent.copy(middleName = it) }, isDarkTheme)
            FormField("Alias", respondent.alias, { respondent = respondent.copy(alias = it) }, isDarkTheme)
            DropdownField("Sex", respondent.sexIdentification, sexOptions, { respondent = respondent.copy(sexIdentification = it) }, isDarkTheme)
            FormField("Age", respondent.age, { respondent = respondent.copy(age = it) }, isDarkTheme)
            DateField("Birthdate", respondent.birthdate, { respondent = respondent.copy(birthdate = it) }, isDarkTheme)
            DropdownField("Civil Status", respondent.civilStatus, civilStatusOptions, { respondent = respondent.copy(civilStatus = it) }, isDarkTheme)
            FormField("Religion", respondent.religion, { respondent = respondent.copy(religion = it) }, isDarkTheme)
            FormField("Nationality", respondent.nationality, { respondent = respondent.copy(nationality = it) }, isDarkTheme)
            FormField("Occupation", respondent.occupation, { respondent = respondent.copy(occupation = it) }, isDarkTheme)
            DropdownField("Relationship to Complainant", respondent.relationshipToComplainant, relationshipOptions, { respondent = respondent.copy(relationshipToComplainant = it) }, isDarkTheme)

            SectionHeader("Respondent Contact Information", isDarkTheme)
            FormField("Contact No.", respondent.cellNumber, { respondent = respondent.copy(cellNumber = it) }, isDarkTheme)
            DropdownField("Purok", respondent.address.purok, purokOptions, { respondent = respondent.copy(address = respondent.address.copy(purok = it)) }, isDarkTheme)

            SectionHeader("Complaint Information", isDarkTheme)
            DateField("Incident Date", caseDetails.incidentDate, { caseDetails = caseDetails.copy(incidentDate = it) }, isDarkTheme)
            DropdownField("Place of the Incident", caseDetails.placeOfIncident.place, incidentPlaceOptions, { caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(place = it)) }, isDarkTheme)
            DropdownField("Purok", caseDetails.placeOfIncident.purok, purokOptions, { caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(purok = it)) }, isDarkTheme)

            SectionHeader("Complaint Details", isDarkTheme)
            OutlinedTextField(
                value = complaintText,
                onValueChange = { complaintText = it },
                modifier = Modifier.fillMaxWidth().height(150.dp),
                placeholder = { 
                    Text(
                        "Enter complaint details...", 
                        color = if (isDarkTheme) DarkInputPlaceholder else TextLight
                    ) 
                },
                textStyle = LocalTextStyle.current.copy(
                    color = if (isDarkTheme) DarkInputText else TextDark
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = if (isDarkTheme) DarkInputText else TextDark,
                    unfocusedTextColor = if (isDarkTheme) DarkInputText else TextDark,
                    focusedLabelColor = if (isDarkTheme) DarkInputFocused else PrimaryPink,
                    unfocusedLabelColor = if (isDarkTheme) DarkInputLabel else TextMedium,
                    focusedBorderColor = if (isDarkTheme) DarkInputFocused else PrimaryPink,
                    unfocusedBorderColor = if (isDarkTheme) DarkInputBorder else TextLight,
                    cursorColor = if (isDarkTheme) DarkInputFocused else PrimaryPink,
                    focusedContainerColor = if (isDarkTheme) DarkInputBackground else White,
                    unfocusedContainerColor = if (isDarkTheme) DarkInputBackground else White,
                    disabledContainerColor = if (isDarkTheme) DarkCard else LightGray,
                    focusedPlaceholderColor = if (isDarkTheme) DarkInputPlaceholder else TextLight,
                    unfocusedPlaceholderColor = if (isDarkTheme) DarkInputPlaceholder else TextLight
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OutlinedButton(
                    onClick = {
                        navController.navigate("landing") {
                            popUpTo("complaint_form") { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = if (isDarkTheme) DarkPrimaryPink else PrimaryPink
                    ),
                    border = BorderStroke(2.dp, if (isDarkTheme) DarkPrimaryPink else PrimaryPink),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        "Cancel", 
                        fontWeight = FontWeight.Medium,
                        color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink
                    )
                }

                Button(
                    onClick = {
                        try {
                            Log.d("ComplaintForm", "Starting form submission...")
                            
                            // Enhanced validation
                            val complainantErrors = validateComplainantData(complainant)
                            val respondentErrors = validateRespondentData(respondent)
                            val caseErrors = validateCaseData(caseDetails.copy(incidentDescription = complaintText.text))
                            
                            val allErrors = complainantErrors + respondentErrors + caseErrors
                            
                            if (allErrors.isNotEmpty()) {
                                Log.e("ComplaintForm", "Validation errors: ${allErrors.joinToString(", ")}")
                                errorMessage.value = "Validation errors: ${allErrors.joinToString(", ")}"
                                showErrorDialog.value = true
                                return@Button
                            }

                            // Check if all required fields are filled
                            val allFieldsFilled = listOf(
                                complainant.lastName, complainant.firstName, complainant.middleName,
                                complainant.sexIdentification, complainant.age, complainant.birthdate,
                                complainant.civilStatus, complainant.religion, complainant.nationality,
                                complainant.occupation, complainant.cellNumber, complainant.address.purok,
                                respondent.lastName, respondent.firstName, respondent.middleName,
                                respondent.alias, respondent.sexIdentification, respondent.age,
                                respondent.birthdate, respondent.civilStatus, respondent.religion,
                                respondent.nationality, respondent.occupation, respondent.relationshipToComplainant,
                                respondent.cellNumber, respondent.address.purok, caseDetails.incidentDate,
                                caseDetails.placeOfIncident.place, caseDetails.placeOfIncident.purok,
                                complaintText.text
                            ).all { it.isNotBlank() }

                            if (!allFieldsFilled) {
                                Log.e("ComplaintForm", "Not all fields are filled")
                                errorMessage.value = "Please fill in all required fields."
                                showErrorDialog.value = true
                                return@Button
                            }

                            Log.d("ComplaintForm", "All validations passed, proceeding with submission...")

                            // Generate secure complaint ID
                            val datePrefix = "CF-$todayDateOnly"
                            val secureSuffix = generateSecureId()
                            val complaintId = "$datePrefix-$secureSuffix"

                            Log.d("ComplaintForm", "Generated complaint ID: $complaintId")

                            // Set fixed location values before encryption
                            val complainantWithFixedLocation = complainant.copy(
                                address = complainant.address.copy(
                                    barangay = "San Pedro",
                                    municipality = "Pagadian City",
                                    province = "Zamboanga Del Sur",
                                    region = "IX"
                                )
                            )
                            
                            val respondentWithFixedLocation = respondent.copy(
                                address = respondent.address.copy(
                                    barangay = "San Pedro",
                                    municipality = "Pagadian City",
                                    province = "Zamboanga Del Sur",
                                    region = "IX"
                                )
                            )
                            
                            val caseDetailsWithFixedLocation = caseDetails.copy(
                                complaintDate = todayFull,
                                incidentDate = caseDetails.incidentDate,
                                incidentDescription = complaintText.text,
                                placeOfIncident = caseDetails.placeOfIncident.copy(
                                    barangay = "San Pedro",
                                    municipality = "Pagadian City",
                                    province = "Zamboanga Del Sur",
                                    region = "IX"
                                )
                            )

                            // Enhanced encryption with validation
                            val encryptedCaseDetails = caseDetailsWithFixedLocation.encryptEnhanced()

                            val encryptedComplaint = Complaint(
                                caseId = complaintId,
                                complainant = complainantWithFixedLocation.encryptEnhanced(),
                                respondent = respondentWithFixedLocation.encryptEnhanced(),
                                caseDetails = encryptedCaseDetails
                            )

                            Log.d("ComplaintForm", "Encrypted complaint created, submitting to Firestore...")

                            db.collection("complaints").document(complaintId).set(encryptedComplaint)
                                .addOnSuccessListener {
                                    Log.d("ComplaintForm", "Complaint submitted successfully!")
                                    complainant = ComplainantDetails()
                                    respondent = RespondentDetails()
                                    caseDetails = CaseDetails(complaintDate = todayFull, incidentDate = todayDateOnly)
                                    complaintText = TextFieldValue()
                                    lastSubmittedSuffix.value = secureSuffix
                                    showSuccessDialog.value = true
                                }
                                .addOnFailureListener { exception ->
                                    Log.e("ComplaintForm", "Failed to submit complaint", exception)
                                    errorMessage.value = "Failed to file complaint: ${exception.message}"
                                    showErrorDialog.value = true
                                }
                        } catch (e: Exception) {
                            Log.e("ComplaintForm", "Unexpected error during submission", e)
                            errorMessage.value = "An unexpected error occurred: ${e.message}"
                            showErrorDialog.value = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
                        contentColor = if (isDarkTheme) DarkTextPrimary else White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("File Now", fontWeight = FontWeight.Bold)
                }
            }

            if (showSuccessDialog.value) {
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = {
                        Button(
                            onClick = {
                                showSuccessDialog.value = false
                                navigateToLandingPage.value = true
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen)
                        ) {
                            Text("Ok")
                        }
                    },
                    title = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.sanpedro1),
                                contentDescription = "Logo 1",
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.sanpedro2),
                                contentDescription = "Logo 2",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    },
                    text = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("You have Successfully Filed a Complaint.", textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("You may Proceed to Barangay San Pedro Pagadian for legal consultation.", textAlign = TextAlign.Center)
                        }
                    }
                )
            }

            if (showErrorDialog.value) {
                AlertDialog(
                    onDismissRequest = { showErrorDialog.value = false },
                    confirmButton = {
                        Button(
                            onClick = { showErrorDialog.value = false },
                            colors = ButtonDefaults.buttonColors(containerColor = ErrorRed)
                        ) {
                            Text("Ok")
                        }
                    },
                    title = {
                        Text("Error", color = ErrorRed, fontWeight = FontWeight.Bold)
                    },
                    text = {
                        Text(errorMessage.value, textAlign = TextAlign.Center)
                    }
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, isDarkTheme: Boolean) {
    Text(
        text = title, 
        fontWeight = FontWeight.SemiBold, 
        fontSize = 16.sp, 
        color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormField(label: String, value: String, onValueChange: (String) -> Unit, isDarkTheme: Boolean) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { 
            Text(
                label, 
                color = if (isDarkTheme) DarkInputLabel else TextDark
            ) 
        },
        textStyle = LocalTextStyle.current.copy(
            color = if (isDarkTheme) DarkInputText else TextDark
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = if (isDarkTheme) DarkInputText else TextDark,
            unfocusedTextColor = if (isDarkTheme) DarkInputText else TextDark,
            focusedLabelColor = if (isDarkTheme) DarkInputFocused else PrimaryPink,
            unfocusedLabelColor = if (isDarkTheme) DarkInputLabel else TextMedium,
            focusedBorderColor = if (isDarkTheme) DarkInputFocused else PrimaryPink,
            unfocusedBorderColor = if (isDarkTheme) DarkInputBorder else TextLight,
            cursorColor = if (isDarkTheme) DarkInputFocused else PrimaryPink,
            focusedContainerColor = if (isDarkTheme) DarkInputBackground else White,
            unfocusedContainerColor = if (isDarkTheme) DarkInputBackground else White,
            disabledContainerColor = if (isDarkTheme) DarkCard else LightGray,
            focusedPlaceholderColor = if (isDarkTheme) DarkInputPlaceholder else TextLight,
            unfocusedPlaceholderColor = if (isDarkTheme) DarkInputPlaceholder else TextLight
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(
                if (isDarkTheme) DarkInputBackground else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 4.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(label: String, selectedOption: String, options: List<String>, onOptionSelected: (String) -> Unit, isDarkTheme: Boolean) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { 
                Text(
                    label, 
                    color = if (isDarkTheme) DarkInputLabel else TextDark
                ) 
            },
            textStyle = LocalTextStyle.current.copy(
                color = if (isDarkTheme) DarkInputText else TextDark
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = if (isDarkTheme) DarkInputText else TextDark,
                unfocusedTextColor = if (isDarkTheme) DarkInputText else TextDark,
                focusedLabelColor = if (isDarkTheme) DarkInputFocused else PrimaryPink,
                unfocusedLabelColor = if (isDarkTheme) DarkInputLabel else TextMedium,
                focusedBorderColor = if (isDarkTheme) DarkInputFocused else PrimaryPink,
                unfocusedBorderColor = if (isDarkTheme) DarkInputBorder else TextLight,
                cursorColor = if (isDarkTheme) DarkInputFocused else PrimaryPink,
                focusedContainerColor = if (isDarkTheme) DarkInputBackground else White,
                unfocusedContainerColor = if (isDarkTheme) DarkInputBackground else White,
                disabledContainerColor = if (isDarkTheme) DarkCard else LightGray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (isDarkTheme) DarkInputBackground else Color.Transparent,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 4.dp),
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        Icons.Filled.ArrowDropDown, 
                        contentDescription = "Dropdown", 
                        tint = if (isDarkTheme) DarkInputFocused else PrimaryPink
                    )
                }
            }
        )

        DropdownMenu(
            expanded = expanded, 
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(
                if (isDarkTheme) DarkInputBackground else VeryLightPink
            )
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { 
                        Text(
                            option, 
                            color = if (isDarkTheme) DarkInputText else TextDark
                        ) 
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = if (isDarkTheme) DarkInputText else TextDark
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(label: String, date: String, onDateSelected: (String) -> Unit, isDarkTheme: Boolean) {
    val context = LocalContext.current
    OutlinedTextField(
        value = date,
        onValueChange = {},
        readOnly = true,
        label = { 
            Text(
                label, 
                color = if (isDarkTheme) DarkInputLabel else TextDark
            ) 
        },
        textStyle = LocalTextStyle.current.copy(
            color = if (isDarkTheme) DarkInputText else TextDark
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = if (isDarkTheme) DarkInputText else TextDark,
            unfocusedTextColor = if (isDarkTheme) DarkInputText else TextDark,
            focusedLabelColor = if (isDarkTheme) DarkInputFocused else PrimaryPink,
            unfocusedLabelColor = if (isDarkTheme) DarkInputLabel else TextMedium,
            focusedBorderColor = if (isDarkTheme) DarkInputFocused else PrimaryPink,
            unfocusedBorderColor = if (isDarkTheme) DarkInputBorder else TextLight,
            cursorColor = if (isDarkTheme) DarkInputFocused else PrimaryPink,
            focusedContainerColor = if (isDarkTheme) DarkInputBackground else White,
            unfocusedContainerColor = if (isDarkTheme) DarkInputBackground else White,
            disabledContainerColor = if (isDarkTheme) DarkCard else LightGray
        ),
        trailingIcon = {
            IconButton(onClick = {
                val calendar = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        onDateSelected("%04d-%02d-%02d".format(year, month + 1, day))
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }) {
                Icon(
                    Icons.Filled.ArrowDropDown, 
                    contentDescription = "Pick Date", 
                    tint = if (isDarkTheme) DarkInputFocused else PrimaryPink
                )
            }
        },
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    )
}