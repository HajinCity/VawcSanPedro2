package com.example.vawcsanpedro2

import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
import com.example.vawcsanpedro2.ui.components.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.zIndex

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .zIndex(2f)
                    .background(if (isDarkTheme) DarkBackground else White)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    // Enhanced Header Card
                    EnhancedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    ) {
                        Text(
                            "File Your Complaint",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Enhanced form layout
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .zIndex(1f)
                            .background(
                                if (isDarkTheme) DarkCard else VeryLightPink,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(24.dp)
                    ) {
                        SectionHeader("Complainant Personal Information", isDarkTheme)
                        EnhancedFormField("Last Name", complainant.lastName, { complainant = complainant.copy(lastName = it) }, isDarkTheme)
                        EnhancedFormField("First Name", complainant.firstName, { complainant = complainant.copy(firstName = it) }, isDarkTheme)
                        EnhancedFormField("Middle Name", complainant.middleName, { complainant = complainant.copy(middleName = it) }, isDarkTheme)
                        EnhancedDropdownField("Sex", complainant.sexIdentification, sexOptions, { complainant = complainant.copy(sexIdentification = it) }, isDarkTheme)
                        EnhancedFormField("Age", complainant.age, { complainant = complainant.copy(age = it) }, isDarkTheme)
                        EnhancedDateField("Birthdate", complainant.birthdate, { complainant = complainant.copy(birthdate = it) }, isDarkTheme)
                        EnhancedDropdownField("Civil Status", complainant.civilStatus, civilStatusOptions, { complainant = complainant.copy(civilStatus = it) }, isDarkTheme)
                        EnhancedFormField("Religion", complainant.religion, { complainant = complainant.copy(religion = it) }, isDarkTheme)
                        EnhancedFormField("Nationality", complainant.nationality, { complainant = complainant.copy(nationality = it) }, isDarkTheme)
                        EnhancedFormField("Occupation", complainant.occupation, { complainant = complainant.copy(occupation = it) }, isDarkTheme)

                        Spacer(modifier = Modifier.height(24.dp))
                        SectionHeader("Complainant Contact Information", isDarkTheme)
                        EnhancedFormField("Contact No.", complainant.cellNumber, { complainant = complainant.copy(cellNumber = it) }, isDarkTheme)
                        EnhancedDropdownField("Purok", complainant.address.purok, purokOptions, { complainant = complainant.copy(address = complainant.address.copy(purok = it)) }, isDarkTheme)

                        Spacer(modifier = Modifier.height(24.dp))
                        SectionHeader("Respondent Personal Information", isDarkTheme)
                        EnhancedFormField("Last Name", respondent.lastName, { respondent = respondent.copy(lastName = it) }, isDarkTheme)
                        EnhancedFormField("First Name", respondent.firstName, { respondent = respondent.copy(firstName = it) }, isDarkTheme)
                        EnhancedFormField("Middle Name", respondent.middleName, { respondent = respondent.copy(middleName = it) }, isDarkTheme)
                        EnhancedFormField("Alias", respondent.alias, { respondent = respondent.copy(alias = it) }, isDarkTheme)
                        EnhancedDropdownField("Sex", respondent.sexIdentification, sexOptions, { respondent = respondent.copy(sexIdentification = it) }, isDarkTheme)
                        EnhancedFormField("Age", respondent.age, { respondent = respondent.copy(age = it) }, isDarkTheme)
                        EnhancedDateField("Birthdate", respondent.birthdate, { respondent = respondent.copy(birthdate = it) }, isDarkTheme)
                        EnhancedDropdownField("Civil Status", respondent.civilStatus, civilStatusOptions, { respondent = respondent.copy(civilStatus = it) }, isDarkTheme)
                        EnhancedFormField("Religion", respondent.religion, { respondent = respondent.copy(religion = it) }, isDarkTheme)
                        EnhancedFormField("Nationality", respondent.nationality, { respondent = respondent.copy(nationality = it) }, isDarkTheme)
                        EnhancedFormField("Occupation", respondent.occupation, { respondent = respondent.copy(occupation = it) }, isDarkTheme)
                        EnhancedDropdownField("Relationship to Complainant", respondent.relationshipToComplainant, relationshipOptions, { respondent = respondent.copy(relationshipToComplainant = it) }, isDarkTheme)

                        Spacer(modifier = Modifier.height(24.dp))
                        SectionHeader("Respondent Contact Information", isDarkTheme)
                        EnhancedFormField("Contact No.", respondent.cellNumber, { respondent = respondent.copy(cellNumber = it) }, isDarkTheme)
                        EnhancedDropdownField("Purok", respondent.address.purok, purokOptions, { respondent = respondent.copy(address = respondent.address.copy(purok = it)) }, isDarkTheme)

                        Spacer(modifier = Modifier.height(24.dp))
                        SectionHeader("Complaint Information", isDarkTheme)
                        EnhancedDateField("Incident Date", caseDetails.incidentDate, { caseDetails = caseDetails.copy(incidentDate = it) }, isDarkTheme)
                        EnhancedDropdownField("Place of the Incident", caseDetails.placeOfIncident.place, incidentPlaceOptions, { caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(place = it)) }, isDarkTheme)
                        EnhancedDropdownField("Purok", caseDetails.placeOfIncident.purok, purokOptions, { caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(purok = it)) }, isDarkTheme)
                        
                        EnhancedFormField("Barangay", caseDetails.placeOfIncident.barangay, { caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(barangay = it)) }, isDarkTheme)
                        EnhancedFormField("Municipality", caseDetails.placeOfIncident.municipality, { caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(municipality = it)) }, isDarkTheme)
                        EnhancedFormField("Province", caseDetails.placeOfIncident.province, { caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(province = it)) }, isDarkTheme)
                        EnhancedFormField("Region", caseDetails.placeOfIncident.region, { caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(region = it)) }, isDarkTheme)

                        Spacer(modifier = Modifier.height(24.dp))
                        SectionHeader("Complaint Details", isDarkTheme)
                        
                        // Enhanced Complaint Details section
                        EnhancedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                        ) {
                            OutlinedTextField(
                                value = complaintText,
                                onValueChange = { complaintText = it },
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                placeholder = { 
                                    Text(
                                        "Enter complaint details...", 
                                        color = if (isDarkTheme) DarkInputPlaceholder else TextLight
                                    ) 
                                },
                                textStyle = MaterialTheme.typography.bodyLarge.copy(
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
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                    focusedPlaceholderColor = if (isDarkTheme) DarkInputPlaceholder else TextLight,
                                    unfocusedPlaceholderColor = if (isDarkTheme) DarkInputPlaceholder else TextLight
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Enhanced Action Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(), 
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        SecondaryButton(
                            text = "Cancel",
                            onClick = {
                                navController.navigate("landing") {
                                    popUpTo("complaint_form") { inclusive = true }
                                }
                            },
                            modifier = Modifier.weight(1f)
                        )

                        PrimaryButton(
                            text = "Submit Complaint",
                            onClick = {
                                try {
                                    Log.d("ComplaintForm", "Starting form submission...")
                                    
                                    // Enhanced validation
                                    val complainantErrors = validateComplainantData(complainant)
                                    val respondentErrors = validateRespondentData(respondent)
                                    val caseErrors = validateCaseData(caseDetails.copy(incidentDescription = complaintText.text))
                                    
                                    if (complainantErrors.isNotEmpty() || respondentErrors.isNotEmpty() || caseErrors.isNotEmpty()) {
                                        val allErrors = complainantErrors + respondentErrors + caseErrors
                                        errorMessage.value = allErrors.joinToString("\n")
                                        showErrorDialog.value = true
                                        return@PrimaryButton
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
                                        return@PrimaryButton
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
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }

    // Enhanced Error Dialog
    if (showErrorDialog.value) {
        AlertDialog(
            onDismissRequest = { showErrorDialog.value = false },
            title = {
                Text(
                    "Validation Error",
                    style = MaterialTheme.typography.headlineSmall,
                    color = ErrorRed
                )
            },
            text = {
                Text(
                    errorMessage.value,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                PrimaryButton(
                    text = "OK",
                    onClick = { showErrorDialog.value = false },
                    modifier = Modifier.width(100.dp)
                )
            }
        )
    }

    // Enhanced Success Dialog
    if (showSuccessDialog.value) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog.value = false },
            title = {
                Text(
                    "Success!",
                    style = MaterialTheme.typography.headlineSmall,
                    color = SuccessGreen
                )
            },
            text = {
                Text(
                    "Your complaint has been submitted successfully. A case number has been generated: ${lastSubmittedSuffix.value}",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                PrimaryButton(
                    text = "OK",
                    onClick = { 
                        showSuccessDialog.value = false
                        navigateToLandingPage.value = true
                    },
                    modifier = Modifier.width(100.dp)
                )
            }
        )
    }
}

// Enhanced Section Header
@Composable
fun SectionHeader(title: String, isDarkTheme: Boolean) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold,
        color = if (isDarkTheme) DarkPrimaryPink else PrimaryPink,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    )
}

// Enhanced Form Field
@Composable
fun EnhancedFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    EnhancedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = modifier.padding(bottom = 16.dp),
        placeholder = "Enter $label"
    )
}

// Enhanced Dropdown Field
@Composable
fun EnhancedDropdownField(
    label: String,
    value: String,
    options: List<String>,
    onValueChange: (String) -> Unit,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    EnhancedDropdown(
        value = value,
        onValueChange = onValueChange,
        label = label,
        options = options,
        modifier = modifier.padding(bottom = 16.dp)
    )
}

// Enhanced Date Field
@Composable
fun EnhancedDateField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    
    EnhancedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = modifier.padding(bottom = 16.dp),
        placeholder = "Select $label",
        readOnly = true,
        onClick = {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    onValueChange(dateFormat.format(calendar.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    )
}