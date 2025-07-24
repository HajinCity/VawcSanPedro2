package com.example.vawcsanpedro2.backendmodel

data class Complaint(
    val caseId: String = "",
    val complainant: ComplainantDetails = ComplainantDetails(),
    val respondent: RespondentDetails = RespondentDetails(),
    val caseDetails: CaseDetails = CaseDetails()
)

data class ComplainantDetails(
    val lastName: String = "",
    val firstName: String = "",
    val middleName: String = "",
    val sexIdentification: String = "",
    val civilStatus: String = "",
    val birthdate: String = "",
    val age: String = "",
    val religion: String = "",
    val cellNumber: String = "",
    val nationality: String = "",
    val occupation: String = "",
    val address: Address = Address()
)

data class RespondentDetails(
    val lastName: String = "",
    val firstName: String = "",
    val middleName: String = "",
    val alias: String = "",
    val sexIdentification: String = "",
    val civilStatus: String = "",
    val birthdate: String = "",
    val age: String = "",
    val religion: String = "",
    val cellNumber: String = "",
    val nationality: String = "",
    val occupation: String = "",
    val relationshipToComplainant: String = "",
    val address: Address = Address()
)

data class CaseDetails(
    val complaintDate: String = "",
    val vawcCase: String = "N/A",      // Optional: dropdown if you define case types
    val subCase: String = "N/A",       // Optional
    val caseStatus: String = "Pending",    // Optional: e.g., “Pending”, “Referred”
    val referredTo: String = "N/A",    // Optional
    val incidentDate: String = "",
    val incidentDescription: String = "",
    val placeOfIncident: IncidentLocation = IncidentLocation()
)

data class Address(
    val purok: String = "",
    val barangay: String = "",
    val municipality: String = "",
    val province: String = "",
    val region: String = ""
)

data class IncidentLocation(
    val place: String = "",
    val purok: String = "",
    val barangay: String = "",
    val municipality: String = "",
    val province: String = "",
    val region: String = ""
)
