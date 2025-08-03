package com.example.vawcsanpedro2

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.navigation.NavHostController
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
import com.example.vawcsanpedro2.backendmodel.*
import com.google.firebase.firestore.FirebaseFirestore
import com.example.vawcsanpedro2.backendmodel.EncryptionTransit.encrypt
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ComplaintFormScreen(navController: NavHostController) {
    val db = FirebaseFirestore.getInstance()
    val lastSubmittedSuffix = remember { mutableStateOf("") }


    val manilaTimeZone = TimeZone.getTimeZone("Asia/Manila")
    val calendar = Calendar.getInstance(manilaTimeZone)

    val fullDateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    fullDateTimeFormat.timeZone = manilaTimeZone
    val todayFull = fullDateTimeFormat.format(calendar.time)

    val dateOnlyFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    dateOnlyFormat.timeZone = manilaTimeZone
    val todayDateOnly = dateOnlyFormat.format(calendar.time)


    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val purokOptions = (1..14).map { "Purok $it" }
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

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHost(hostState = scaffoldState.snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                "File Your Complaint",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader("Complainant Personal Information")
            FormField("Last Name", complainant.lastName) { complainant = complainant.copy(lastName = it) }
            FormField("First Name", complainant.firstName) { complainant = complainant.copy(firstName = it) }
            FormField("Middle Name", complainant.middleName) { complainant = complainant.copy(middleName = it) }
            DropdownField("Sex", complainant.sexIdentification, sexOptions) { complainant = complainant.copy(sexIdentification = it) }
            FormField("Age", complainant.age) { complainant = complainant.copy(age = it) }
            DateField("Birthdate", complainant.birthdate) { complainant = complainant.copy(birthdate = it) }
            DropdownField("Civil Status", complainant.civilStatus, civilStatusOptions) { complainant = complainant.copy(civilStatus = it) }
            FormField("Religion", complainant.religion) { complainant = complainant.copy(religion = it) }
            FormField("Nationality", complainant.nationality) { complainant = complainant.copy(nationality = it) }
            FormField("Occupation", complainant.occupation) { complainant = complainant.copy(occupation = it) }

            SectionHeader("Complainant Contact Information")
            FormField("Contact No.", complainant.cellNumber) { complainant = complainant.copy(cellNumber = it) }
            FormField("Barangay", complainant.address.barangay) {
                complainant = complainant.copy(address = complainant.address.copy(barangay = it))
            }
            DropdownField("Purok", complainant.address.purok, purokOptions) {
                complainant = complainant.copy(address = complainant.address.copy(purok = it))
            }
            FormField("Municipality", complainant.address.municipality) {
                complainant = complainant.copy(address = complainant.address.copy(municipality = it))
            }
            FormField("Province", complainant.address.province) {
                complainant = complainant.copy(address = complainant.address.copy(province = it))
            }
            FormField("Region", complainant.address.region) {
                complainant = complainant.copy(address = complainant.address.copy(region = it))
            }

            SectionHeader("Respondent Personal Information")
            FormField("Last Name", respondent.lastName) { respondent = respondent.copy(lastName = it) }
            FormField("First Name", respondent.firstName) { respondent = respondent.copy(firstName = it) }
            FormField("Middle Name", respondent.middleName) { respondent = respondent.copy(middleName = it) }
            FormField("Alias", respondent.alias) { respondent = respondent.copy(alias = it) }
            DropdownField("Sex", respondent.sexIdentification, sexOptions) { respondent = respondent.copy(sexIdentification = it) }
            FormField("Age", respondent.age) { respondent = respondent.copy(age = it) }
            DateField("Birthdate", respondent.birthdate) { respondent = respondent.copy(birthdate = it) }
            DropdownField("Civil Status", respondent.civilStatus, civilStatusOptions) { respondent = respondent.copy(civilStatus = it) }
            FormField("Religion", respondent.religion) { respondent = respondent.copy(religion = it) }
            FormField("Nationality", respondent.nationality) { respondent = respondent.copy(nationality = it) }
            FormField("Occupation", respondent.occupation) { respondent = respondent.copy(occupation = it) }
            DropdownField("Relationship to Complainant", respondent.relationshipToComplainant, relationshipOptions) {
                respondent = respondent.copy(relationshipToComplainant = it)
            }

            SectionHeader("Respondent Contact Information")
            FormField("Contact No.", respondent.cellNumber) { respondent = respondent.copy(cellNumber = it) }
            FormField("Barangay", respondent.address.barangay) {
                respondent = respondent.copy(address = respondent.address.copy(barangay = it))
            }
            DropdownField("Purok", respondent.address.purok, purokOptions) {
                respondent = respondent.copy(address = respondent.address.copy(purok = it))
            }
            FormField("Municipality", respondent.address.municipality) {
                respondent = respondent.copy(address = respondent.address.copy(municipality = it))
            }
            FormField("Province", respondent.address.province) {
                respondent = respondent.copy(address = respondent.address.copy(province = it))
            }
            FormField("Region", respondent.address.region) {
                respondent = respondent.copy(address = respondent.address.copy(region = it))
            }


            SectionHeader("Complaint Information")
            DateField("Incident Date", caseDetails.incidentDate) {
                caseDetails = caseDetails.copy(incidentDate = it)
            }
            DropdownField("Place of the Incident", caseDetails.placeOfIncident.place, incidentPlaceOptions) {
                caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(place = it))
            }

            DropdownField("Purok", caseDetails.placeOfIncident.purok, purokOptions) {
                caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(purok = it))
            }
            FormField("Barangay", caseDetails.placeOfIncident.barangay) {
                caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(barangay = it))
            }
            FormField("Municipality", caseDetails.placeOfIncident.municipality) {
                caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(municipality = it))
            }
            FormField("Province", caseDetails.placeOfIncident.province) {
                caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(province = it))
            }
            FormField("Region", caseDetails.placeOfIncident.region) {
                caseDetails = caseDetails.copy(placeOfIncident = caseDetails.placeOfIncident.copy(region = it))
            }

            SectionHeader("Complaint Details")
            OutlinedTextField(
                value = complaintText,
                onValueChange = { complaintText = it },
                modifier = Modifier.fillMaxWidth().height(150.dp),
                placeholder = { Text("Enter complaint details...") }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OutlinedButton(onClick = {
                    navController.navigate("landing") {
                        popUpTo("complaint_form") { inclusive = true }
                    }
                }) {
                    Text("Cancel")
                }

                Button(onClick = {
                    val allFieldsFilled = listOf(
                        complainant.lastName, complainant.firstName, complainant.middleName,
                        complainant.sexIdentification, complainant.age, complainant.birthdate,
                        complainant.civilStatus, complainant.religion, complainant.nationality,
                        complainant.occupation, complainant.cellNumber, complainant.address.barangay,
                        complainant.address.purok, respondent.lastName, respondent.firstName,
                        respondent.middleName, respondent.alias, respondent.sexIdentification,
                        respondent.age, respondent.birthdate, respondent.civilStatus,
                        respondent.religion, respondent.nationality, respondent.occupation,
                        respondent.relationshipToComplainant, respondent.cellNumber,
                        respondent.address.barangay, respondent.address.purok,
                        caseDetails.incidentDate, caseDetails.placeOfIncident.place,
                        caseDetails.placeOfIncident.purok, caseDetails.placeOfIncident.barangay,
                        complaintText.text
                    ).all { it.isNotBlank() }

                    if (!allFieldsFilled) {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Please fill in all required fields.")
                        }
                        return@Button
                    }

                    // ✅ Declare datePrefix here
                    val datePrefix = "CF-$todayDateOnly"

                    // Generate Firestore random ID, extract last 4 characters
                    val randomDocId = db.collection("complaints").document().id
                    val suffix = randomDocId.takeLast(4)
                    val complaintId = "$datePrefix-$suffix"  // e.g. CF-2025-08-03-5z9k

                    val encryptedCaseDetails = caseDetails.copy(
                        complaintDate = todayFull,
                        incidentDate = caseDetails.incidentDate,
                        incidentDescription = complaintText.text,
                        placeOfIncident = caseDetails.placeOfIncident
                    ).encrypt(excludeFields = listOf("complaintDate", "incidentDate"))

                    val encryptedComplaint = Complaint(
                        caseId = complaintId,
                        complainant = complainant.encrypt(),
                        respondent = respondent.encrypt(),
                        caseDetails = encryptedCaseDetails.copy(
                            complaintDate = todayFull,
                            incidentDate = caseDetails.incidentDate
                        )
                    )

                    db.collection("complaints").document(complaintId).set(encryptedComplaint)
                        .addOnSuccessListener {
                            complainant = ComplainantDetails()
                            respondent = RespondentDetails()
                            caseDetails = CaseDetails(complaintDate = todayFull, incidentDate = todayDateOnly)
                            complaintText = TextFieldValue()
                            lastSubmittedSuffix.value = suffix // For dialog display
                            showSuccessDialog.value = true
                        }
                        .addOnFailureListener {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Failed to file complaint.")
                            }
                        }
                }) {
                    Text("File Now")
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
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF32F35A))
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
        }
    }
}


@Composable
fun SectionHeader(title: String) {
    Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
}

@Composable
fun FormField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.Black) },
        textStyle = LocalTextStyle.current.copy(color = Color.Black),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            cursorColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}


@Composable
fun DropdownField(label: String, selectedOption: String, options: List<String>, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label, color = Color.Black) },
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown", tint = Color.Black)
                }
            }
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}



@Composable
fun DateField(label: String, date: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    OutlinedTextField(
        value = date,
        onValueChange = {},
        readOnly = true,
        label = { Text(label, color = Color.Black) },
        textStyle = LocalTextStyle.current.copy(color = Color.Black),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            cursorColor = Color.Black
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
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Pick Date", tint = Color.Black)
            }
        },
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    )
}