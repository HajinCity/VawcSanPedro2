package com.example.vawcsanpedro2.backendmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

object EncryptionTransit {

    private var secretKey: String = ""
    private var initVector: String = ""

    fun init(secretKey: String, initVector: String) {
        this.secretKey = secretKey
        this.initVector = initVector
    }

    fun getSecretKey(): String = secretKey
    fun getInitVector(): String = initVector

    suspend fun encryptTextAsync(text: String): String {
        if (text.isBlank()) return ""
        return withContext(Dispatchers.IO) {
            try {
                val response = EncryptionClient.api.encryptText(EncryptionRequest(text)).awaitResponse()
                if (response.isSuccessful) {
                    response.body()?.encrypted ?: ""
                } else {
                    ""
                }
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }
    }

    suspend fun ComplainantDetails.encrypt(): ComplainantDetails = this.copy(
        lastName = encryptTextAsync(lastName),
        firstName = encryptTextAsync(firstName),
        middleName = encryptTextAsync(middleName),
        sexIdentification = encryptTextAsync(sexIdentification),
        civilStatus = encryptTextAsync(civilStatus),
        birthdate = encryptTextAsync(birthdate),
        age = encryptTextAsync(age),
        religion = encryptTextAsync(religion),
        cellNumber = encryptTextAsync(cellNumber),
        nationality = encryptTextAsync(nationality),
        occupation = encryptTextAsync(occupation),
        address = address.encrypt()
    )

    suspend fun RespondentDetails.encrypt(): RespondentDetails = this.copy(
        lastName = encryptTextAsync(lastName),
        firstName = encryptTextAsync(firstName),
        middleName = encryptTextAsync(middleName),
        alias = encryptTextAsync(alias),
        sexIdentification = encryptTextAsync(sexIdentification),
        civilStatus = encryptTextAsync(civilStatus),
        birthdate = encryptTextAsync(birthdate),
        age = encryptTextAsync(age),
        religion = encryptTextAsync(religion),
        cellNumber = encryptTextAsync(cellNumber),
        nationality = encryptTextAsync(nationality),
        occupation = encryptTextAsync(occupation),
        relationshipToComplainant = encryptTextAsync(relationshipToComplainant),
        address = address.encrypt()
    )

    suspend fun CaseDetails.encrypt(): CaseDetails = this.copy(
        caseNumber = encryptTextAsync(caseNumber),
        complaintDate = encryptTextAsync(complaintDate),
        vawcCase = encryptTextAsync(vawcCase),
        subCase = encryptTextAsync(subCase),
        caseStatus = encryptTextAsync(caseStatus),
        referredTo = encryptTextAsync(referredTo),
        incidentDate = encryptTextAsync(incidentDate),
        incidentDescription = encryptTextAsync(incidentDescription),
        placeOfIncident = placeOfIncident.encrypt()
    )

    suspend fun Address.encrypt(): Address = this.copy(
        purok = encryptTextAsync(purok),
        barangay = encryptTextAsync(barangay),
        municipality = encryptTextAsync(municipality),
        province = encryptTextAsync(province),
        region = encryptTextAsync(region)
    )

    suspend fun IncidentLocation.encrypt(): IncidentLocation = this.copy(
        place = encryptTextAsync(place),
        purok = encryptTextAsync(purok),
        barangay = encryptTextAsync(barangay),
        municipality = encryptTextAsync(municipality),
        province = encryptTextAsync(province),
        region = encryptTextAsync(region)
    )

    suspend fun Complaint.encrypt(): Complaint = this.copy(
        complainant = complainant.encrypt(),
        respondent = respondent.encrypt(),
        caseDetails = caseDetails.encrypt()
    )
}
