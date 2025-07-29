package com.example.vawcsanpedro2.backendmodel

import android.util.Base64
import com.example.vawcsanpedro2.BuildConfig
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object EncryptionTransit {

    private lateinit var SECRET_KEY: String
    private lateinit var INIT_VECTOR: String

    // Call this during app initialization
    fun initFromBuildConfig() {
        SECRET_KEY = BuildConfig.ENCRYPTION_SECRET_KEY
        INIT_VECTOR = BuildConfig.ENCRYPTION_INIT_VECTOR
    }

    fun init(secretKey: String, initVector: String) {
        SECRET_KEY = secretKey
        INIT_VECTOR = initVector
    }

    fun encrypt(str: String): String {
        if (str.isBlank()) return ""
        val iv = IvParameterSpec(INIT_VECTOR.toByteArray(Charsets.UTF_8))
        val keySpec = SecretKeySpec(SECRET_KEY.toByteArray(Charsets.UTF_8), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv)
        val encrypted = cipher.doFinal(str.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(encrypted, Base64.NO_WRAP)
    }

    // Encrypt Complainant
    fun ComplainantDetails.encrypt(): ComplainantDetails = this.copy(
        lastName = encrypt(lastName),
        firstName = encrypt(firstName),
        middleName = encrypt(middleName),
        sexIdentification = encrypt(sexIdentification),
        civilStatus = encrypt(civilStatus),
        birthdate = encrypt(birthdate),
        age = encrypt(age),
        religion = encrypt(religion),
        cellNumber = encrypt(cellNumber),
        nationality = encrypt(nationality),
        occupation = encrypt(occupation),
        address = address.encrypt()
    )

    // Encrypt Respondent
    fun RespondentDetails.encrypt(): RespondentDetails = this.copy(
        lastName = encrypt(lastName),
        firstName = encrypt(firstName),
        middleName = encrypt(middleName),
        alias = encrypt(alias),
        sexIdentification = encrypt(sexIdentification),
        civilStatus = encrypt(civilStatus),
        birthdate = encrypt(birthdate),
        age = encrypt(age),
        religion = encrypt(religion),
        cellNumber = encrypt(cellNumber),
        nationality = encrypt(nationality),
        occupation = encrypt(occupation),
        relationshipToComplainant = encrypt(relationshipToComplainant),
        address = address.encrypt()
    )

    // Encrypt Case Details
    fun CaseDetails.encrypt(): CaseDetails = this.copy(
        complaintDate = encrypt(complaintDate),
        vawcCase = encrypt(vawcCase),
        subCase = encrypt(subCase),
        caseStatus = encrypt(caseStatus),
        referredTo = encrypt(referredTo),
        incidentDate = encrypt(incidentDate),
        incidentDescription = encrypt(incidentDescription),
        placeOfIncident = placeOfIncident.encrypt()
    )

    // Encrypt Address
    fun Address.encrypt(): Address = this.copy(
        purok = encrypt(purok),
        barangay = encrypt(barangay),
        municipality = encrypt(municipality),
        province = encrypt(province),
        region = encrypt(region)
    )

    // Encrypt IncidentLocation
    fun IncidentLocation.encrypt(): IncidentLocation = this.copy(
        place = encrypt(place),
        purok = encrypt(purok),
        barangay = encrypt(barangay),
        municipality = encrypt(municipality),
        province = encrypt(province),
        region = encrypt(region)
    )
}