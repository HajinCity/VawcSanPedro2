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
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
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
import com.example.vawcsanpedro2.backendmodel.EncryptionTransit.encrypt
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ComplaintFormScreen(navController: NavHostController) {

    val db = FirebaseFirestore.getInstance()
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val today = dateFormatter.format(Date())
    val year = SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())
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
        mutableStateOf(CaseDetails(complaintDate = today, incidentDate = today))
    }
    var complaintText by remember { mutableStateOf(TextFieldValue()) }
    val scrollState = rememberScrollState()

    val showSuccessDialog = remember { mutableStateOf(false) }

    // ⬇ Navigation Trigger
    val navigateToLandingPage = remember { mutableStateOf(false) }

    if (navigateToLandingPage.value) {
        LaunchedEffect(Unit) {
            navController.navigate("landing") { // ✅ Correct route name
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
                    coroutineScope.launch {
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
                            scaffoldState.snackbarHostState.showSnackbar("Please fill in all required fields.")
                            return@launch
                        }

                        val complaintId = "CASE-\${System.currentTimeMillis()}"

                        try {
                            suspend fun encryptText(text: String): String {
                                val response = EncryptionClient.api.encryptText(EncryptionRequest(text)).execute()
                                if (response.isSuccessful && response.body() != null) {
                                    return response.body()!!.encrypted
                                } else {
                                    throw Exception("Encryption failed for text: \$text")
                                }
                            }

                            val encryptedComplainant = complainant.copy(
                                lastName = encryptText(complainant.lastName),
                                firstName = encryptText(complainant.firstName),
                                middleName = encryptText(complainant.middleName),
                                sexIdentification = encryptText(complainant.sexIdentification),
                                civilStatus = encryptText(complainant.civilStatus),
                                birthdate = encryptText(complainant.birthdate),
                                age = encryptText(complainant.age),
                                religion = encryptText(complainant.religion),
                                cellNumber = encryptText(complainant.cellNumber),
                                nationality = encryptText(complainant.nationality),
                                occupation = encryptText(complainant.occupation),
                                address = complainant.address.copy(
                                    purok = encryptText(complainant.address.purok),
                                    barangay = encryptText(complainant.address.barangay),
                                    municipality = encryptText(complainant.address.municipality),
                                    province = encryptText(complainant.address.province),
                                    region = encryptText(complainant.address.region)
                                )
                            )

                            val encryptedRespondent = respondent.copy(
                                lastName = encryptText(respondent.lastName),
                                firstName = encryptText(respondent.firstName),
                                middleName = encryptText(respondent.middleName),
                                alias = encryptText(respondent.alias),
                                sexIdentification = encryptText(respondent.sexIdentification),
                                civilStatus = encryptText(respondent.civilStatus),
                                birthdate = encryptText(respondent.birthdate),
                                age = encryptText(respondent.age),
                                religion = encryptText(respondent.religion),
                                cellNumber = encryptText(respondent.cellNumber),
                                nationality = encryptText(respondent.nationality),
                                occupation = encryptText(respondent.occupation),
                                relationshipToComplainant = encryptText(respondent.relationshipToComplainant),
                                address = respondent.address.copy(
                                    purok = encryptText(respondent.address.purok),
                                    barangay = encryptText(respondent.address.barangay),
                                    municipality = encryptText(respondent.address.municipality),
                                    province = encryptText(respondent.address.province),
                                    region = encryptText(respondent.address.region)
                                )
                            )

                            val encryptedCaseDetails = caseDetails.copy(
                                caseNumber = encryptText(complaintId),
                                complaintDate = encryptText(today),
                                vawcCase = encryptText(caseDetails.vawcCase),
                                subCase = encryptText(caseDetails.subCase),
                                caseStatus = encryptText(caseDetails.caseStatus),
                                referredTo = encryptText(caseDetails.referredTo),
                                incidentDate = encryptText(caseDetails.incidentDate),
                                incidentDescription = encryptText(complaintText.text),
                                placeOfIncident = caseDetails.placeOfIncident.copy(
                                    place = encryptText(caseDetails.placeOfIncident.place),
                                    purok = encryptText(caseDetails.placeOfIncident.purok),
                                    barangay = encryptText(caseDetails.placeOfIncident.barangay),
                                    municipality = encryptText(caseDetails.placeOfIncident.municipality),
                                    province = encryptText(caseDetails.placeOfIncident.province),
                                    region = encryptText(caseDetails.placeOfIncident.region)
                                )
                            )

                            val encryptedComplaint = Complaint(
                                caseId = complaintId,
                                complainant = encryptedComplainant,
                                respondent = encryptedRespondent,
                                caseDetails = encryptedCaseDetails
                            )

                            db.collection("complaints").document(complaintId).set(encryptedComplaint)
                                .addOnSuccessListener {
                                    complainant = ComplainantDetails()
                                    respondent = RespondentDetails()
                                    caseDetails = CaseDetails(complaintDate = today, incidentDate = today)
                                    complaintText = TextFieldValue()
                                    showSuccessDialog.value = true
                                }
                                .addOnFailureListener {
                                    coroutineScope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar("Failed to file complaint.")
                                    }
                                }

                        } catch (e: Exception) {
                            scaffoldState.snackbarHostState.showSnackbar("Encryption failed: \${e.localizedMessage}")
                        }
                    }
                }) {
                    Text("File Now")
                }





                // ✅ Success Dialog
                    if (showSuccessDialog.value) {
                        AlertDialog(
                            onDismissRequest = { },
                            confirmButton = {
                                Button(onClick = {
                                    showSuccessDialog.value = false
                                    navigateToLandingPage.value = true
                                }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF32F35A))) {
                                    Text("Ok")
                                }
                            },
                            title = {
                                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                                    Icon(painter = painterResource(id = R.drawable.sanpedro1), contentDescription = "Logo 1", modifier = Modifier.size(40.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(painter = painterResource(id = R.drawable.sanpedro2), contentDescription = "Logo 2", modifier = Modifier.size(40.dp))
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

    }
