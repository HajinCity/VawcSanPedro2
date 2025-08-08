package com.example.vawcsanpedro2.backendmodel

import android.util.Log

object EnhancedEncryptionTransit {
    private const val TAG = "EnhancedEncryptionTransit"

    // Enhanced encryption methods using SecurityManager
    fun ComplainantDetails.encryptEnhanced(): ComplainantDetails {
        return try {
            this.copy(
                lastName = SecurityManager.encrypt(SecurityManager.sanitizeInput(lastName)),
                firstName = SecurityManager.encrypt(SecurityManager.sanitizeInput(firstName)),
                middleName = SecurityManager.encrypt(SecurityManager.sanitizeInput(middleName)),
                sexIdentification = SecurityManager.encrypt(SecurityManager.sanitizeInput(sexIdentification)),
                civilStatus = SecurityManager.encrypt(SecurityManager.sanitizeInput(civilStatus)),
                birthdate = SecurityManager.encrypt(SecurityManager.sanitizeInput(birthdate)),
                age = SecurityManager.encrypt(SecurityManager.sanitizeInput(age)),
                religion = SecurityManager.encrypt(SecurityManager.sanitizeInput(religion)),
                cellNumber = SecurityManager.encrypt(SecurityManager.sanitizeInput(cellNumber)),
                nationality = SecurityManager.encrypt(SecurityManager.sanitizeInput(nationality)),
                occupation = SecurityManager.encrypt(SecurityManager.sanitizeInput(occupation)),
                address = address.encryptEnhanced()
            )
        } catch (e: Exception) {
            Log.e(TAG, "Failed to encrypt complainant details", e)
            throw SecurityException("Complainant encryption failed", e)
        }
    }

    fun RespondentDetails.encryptEnhanced(): RespondentDetails {
        return try {
            this.copy(
                lastName = SecurityManager.encrypt(SecurityManager.sanitizeInput(lastName)),
                firstName = SecurityManager.encrypt(SecurityManager.sanitizeInput(firstName)),
                middleName = SecurityManager.encrypt(SecurityManager.sanitizeInput(middleName)),
                alias = SecurityManager.encrypt(SecurityManager.sanitizeInput(alias)),
                sexIdentification = SecurityManager.encrypt(SecurityManager.sanitizeInput(sexIdentification)),
                civilStatus = SecurityManager.encrypt(SecurityManager.sanitizeInput(civilStatus)),
                birthdate = SecurityManager.encrypt(SecurityManager.sanitizeInput(birthdate)),
                age = SecurityManager.encrypt(SecurityManager.sanitizeInput(age)),
                religion = SecurityManager.encrypt(SecurityManager.sanitizeInput(religion)),
                cellNumber = SecurityManager.encrypt(SecurityManager.sanitizeInput(cellNumber)),
                nationality = SecurityManager.encrypt(SecurityManager.sanitizeInput(nationality)),
                occupation = SecurityManager.encrypt(SecurityManager.sanitizeInput(occupation)),
                relationshipToComplainant = SecurityManager.encrypt(SecurityManager.sanitizeInput(relationshipToComplainant)),
                address = address.encryptEnhanced()
            )
        } catch (e: Exception) {
            Log.e(TAG, "Failed to encrypt respondent details", e)
            throw SecurityException("Respondent encryption failed", e)
        }
    }

    fun CaseDetails.encryptEnhanced(): CaseDetails {
        return try {
            this.copy(
                complaintDate = SecurityManager.encrypt(SecurityManager.sanitizeInput(complaintDate)),
                vawcCase = SecurityManager.encrypt(SecurityManager.sanitizeInput(vawcCase)),
                subCase = SecurityManager.encrypt(SecurityManager.sanitizeInput(subCase)),
                caseStatus = SecurityManager.encrypt(SecurityManager.sanitizeInput(caseStatus)),
                referredTo = SecurityManager.encrypt(SecurityManager.sanitizeInput(referredTo)),
                incidentDate = SecurityManager.encrypt(SecurityManager.sanitizeInput(incidentDate)),
                incidentDescription = SecurityManager.encrypt(SecurityManager.sanitizeInput(incidentDescription)),
                placeOfIncident = placeOfIncident.encryptEnhanced()
            )
        } catch (e: Exception) {
            Log.e(TAG, "Failed to encrypt case details", e)
            throw SecurityException("Case details encryption failed", e)
        }
    }

    fun Address.encryptEnhanced(): Address {
        return try {
            this.copy(
                purok = SecurityManager.encrypt(SecurityManager.sanitizeInput(purok)),
                barangay = SecurityManager.encrypt(SecurityManager.sanitizeInput(barangay)),
                municipality = SecurityManager.encrypt(SecurityManager.sanitizeInput(municipality)),
                province = SecurityManager.encrypt(SecurityManager.sanitizeInput(province)),
                region = SecurityManager.encrypt(SecurityManager.sanitizeInput(region))
            )
        } catch (e: Exception) {
            Log.e(TAG, "Failed to encrypt address", e)
            throw SecurityException("Address encryption failed", e)
        }
    }

    fun IncidentLocation.encryptEnhanced(): IncidentLocation {
        return try {
            this.copy(
                place = SecurityManager.encrypt(SecurityManager.sanitizeInput(place)),
                purok = SecurityManager.encrypt(SecurityManager.sanitizeInput(purok)),
                barangay = SecurityManager.encrypt(SecurityManager.sanitizeInput(barangay)),
                municipality = SecurityManager.encrypt(SecurityManager.sanitizeInput(municipality)),
                province = SecurityManager.encrypt(SecurityManager.sanitizeInput(province)),
                region = SecurityManager.encrypt(SecurityManager.sanitizeInput(region))
            )
        } catch (e: Exception) {
            Log.e(TAG, "Failed to encrypt incident location", e)
            throw SecurityException("Incident location encryption failed", e)
        }
    }

    // Validation methods
    fun validateComplainantData(complainant: ComplainantDetails): List<String> {
        val errors = mutableListOf<String>()
        
        if (!SecurityManager.validateName(complainant.lastName)) {
            errors.add("Invalid last name format")
        }
        if (!SecurityManager.validateName(complainant.firstName)) {
            errors.add("Invalid first name format")
        }
        if (!SecurityManager.validateAge(complainant.age)) {
            errors.add("Invalid age")
        }
        if (!SecurityManager.validatePhone(complainant.cellNumber)) {
            errors.add("Invalid phone number format")
        }
        if (!SecurityManager.validateAddress(complainant.address.barangay)) {
            errors.add("Invalid barangay address")
        }
        
        return errors
    }

    fun validateRespondentData(respondent: RespondentDetails): List<String> {
        val errors = mutableListOf<String>()
        
        if (!SecurityManager.validateName(respondent.lastName)) {
            errors.add("Invalid respondent last name format")
        }
        if (!SecurityManager.validateName(respondent.firstName)) {
            errors.add("Invalid respondent first name format")
        }
        if (!SecurityManager.validateAge(respondent.age)) {
            errors.add("Invalid respondent age")
        }
        if (!SecurityManager.validatePhone(respondent.cellNumber)) {
            errors.add("Invalid respondent phone number format")
        }
        if (!SecurityManager.validateAddress(respondent.address.barangay)) {
            errors.add("Invalid respondent barangay address")
        }
        
        return errors
    }

    fun validateCaseData(caseDetails: CaseDetails): List<String> {
        val errors = mutableListOf<String>()
        
        if (caseDetails.incidentDescription.isBlank() || caseDetails.incidentDescription.length < 10) {
            errors.add("Incident description must be at least 10 characters")
        }
        if (!SecurityManager.validateAddress(caseDetails.placeOfIncident.place)) {
            errors.add("Invalid incident place")
        }
        
        return errors
    }
}
