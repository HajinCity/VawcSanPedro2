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
                birthdate = birthdate, // Excluded from encryption
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
                birthdate = birthdate, // Excluded from encryption
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
                complaintDate = complaintDate, // Excluded from encryption
                vawcCase = SecurityManager.encrypt(SecurityManager.sanitizeInput(vawcCase)),
                subCase = SecurityManager.encrypt(SecurityManager.sanitizeInput(subCase)),
                caseStatus = SecurityManager.encrypt(SecurityManager.sanitizeInput(caseStatus)),
                referredTo = SecurityManager.encrypt(SecurityManager.sanitizeInput(referredTo)),
                incidentDate = incidentDate, // Excluded from encryption
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

    // Enhanced validation methods with detailed field-level error messages
    data class FieldError(val fieldName: String, val message: String)
    
    fun validateComplainantData(complainant: ComplainantDetails): List<FieldError> {
        val errors = mutableListOf<FieldError>()
        
        // Check for empty required fields
        if (complainant.lastName.isBlank()) {
            errors.add(FieldError("lastName", "Last Name is required"))
        } else if (!SecurityManager.validateName(complainant.lastName)) {
            errors.add(FieldError("lastName", "Invalid last name format"))
        }
        
        if (complainant.firstName.isBlank()) {
            errors.add(FieldError("firstName", "First Name is required"))
        } else if (!SecurityManager.validateName(complainant.firstName)) {
            errors.add(FieldError("firstName", "Invalid first name format"))
        }
        
        if (complainant.middleName.isBlank()) {
            errors.add(FieldError("middleName", "Middle Name is required"))
        }
        
        if (complainant.sexIdentification.isBlank()) {
            errors.add(FieldError("sexIdentification", "Sex is required"))
        }
        
        if (complainant.age.isBlank()) {
            errors.add(FieldError("age", "Age is required"))
        } else if (!SecurityManager.validateAge(complainant.age)) {
            errors.add(FieldError("age", "Invalid age format"))
        }
        
        if (complainant.birthdate.isBlank()) {
            errors.add(FieldError("birthdate", "Birthdate is required"))
        }
        
        if (complainant.civilStatus.isBlank()) {
            errors.add(FieldError("civilStatus", "Civil Status is required"))
        }
        
        if (complainant.religion.isBlank()) {
            errors.add(FieldError("religion", "Religion is required"))
        }
        
        if (complainant.nationality.isBlank()) {
            errors.add(FieldError("nationality", "Nationality is required"))
        }
        
        if (complainant.occupation.isBlank()) {
            errors.add(FieldError("occupation", "Occupation is required"))
        }
        
        if (complainant.cellNumber.isBlank()) {
            errors.add(FieldError("cellNumber", "Cell Number is required"))
        } else if (!SecurityManager.validatePhone(complainant.cellNumber)) {
            errors.add(FieldError("cellNumber", "Invalid phone number format"))
        }
        
        if (complainant.address.purok.isBlank()) {
            errors.add(FieldError("purok", "Purok is required"))
        }
        
        return errors
    }

    fun validateRespondentData(respondent: RespondentDetails): List<FieldError> {
        val errors = mutableListOf<FieldError>()
        
        // Check for empty required fields
        if (respondent.lastName.isBlank()) {
            errors.add(FieldError("respondent_lastName", "Respondent Last Name is required"))
        } else if (!SecurityManager.validateName(respondent.lastName)) {
            errors.add(FieldError("respondent_lastName", "Invalid respondent last name format"))
        }
        
        if (respondent.firstName.isBlank()) {
            errors.add(FieldError("respondent_firstName", "Respondent First Name is required"))
        } else if (!SecurityManager.validateName(respondent.firstName)) {
            errors.add(FieldError("respondent_firstName", "Invalid respondent first name format"))
        }
        
        if (respondent.middleName.isBlank()) {
            errors.add(FieldError("respondent_middleName", "Respondent Middle Name is required"))
        }
        
        if (respondent.alias.isBlank()) {
            errors.add(FieldError("respondent_alias", "Respondent Alias is required"))
        }
        
        if (respondent.sexIdentification.isBlank()) {
            errors.add(FieldError("respondent_sexIdentification", "Respondent Sex is required"))
        }
        
        if (respondent.age.isBlank()) {
            errors.add(FieldError("respondent_age", "Respondent Age is required"))
        } else if (!SecurityManager.validateAge(respondent.age)) {
            errors.add(FieldError("respondent_age", "Invalid respondent age format"))
        }
        
        if (respondent.birthdate.isBlank()) {
            errors.add(FieldError("respondent_birthdate", "Respondent Birthdate is required"))
        }
        
        if (respondent.civilStatus.isBlank()) {
            errors.add(FieldError("respondent_civilStatus", "Respondent Civil Status is required"))
        }
        
        if (respondent.religion.isBlank()) {
            errors.add(FieldError("respondent_religion", "Respondent Religion is required"))
        }
        
        if (respondent.nationality.isBlank()) {
            errors.add(FieldError("respondent_nationality", "Respondent Nationality is required"))
        }
        
        if (respondent.occupation.isBlank()) {
            errors.add(FieldError("respondent_occupation", "Respondent Occupation is required"))
        }
        
        if (respondent.relationshipToComplainant.isBlank()) {
            errors.add(FieldError("respondent_relationship", "Relationship to Complainant is required"))
        }
        
        if (respondent.cellNumber.isBlank()) {
            errors.add(FieldError("respondent_cellNumber", "Respondent Cell Number is required"))
        } else if (!SecurityManager.validatePhone(respondent.cellNumber)) {
            errors.add(FieldError("respondent_cellNumber", "Invalid respondent phone number format"))
        }
        
        if (respondent.address.purok.isBlank()) {
            errors.add(FieldError("respondent_purok", "Respondent Purok is required"))
        }
        
        return errors
    }

    fun validateCaseData(caseDetails: CaseDetails): List<FieldError> {
        val errors = mutableListOf<FieldError>()
        
        if (caseDetails.incidentDate.isBlank()) {
            errors.add(FieldError("incidentDate", "Incident Date is required"))
        }
        
        if (caseDetails.placeOfIncident.place.isBlank()) {
            errors.add(FieldError("incidentPlace", "Place of Incident is required"))
        } else if (!SecurityManager.validateAddress(caseDetails.placeOfIncident.place)) {
            errors.add(FieldError("incidentPlace", "Invalid incident place format"))
        }
        
        if (caseDetails.placeOfIncident.purok.isBlank()) {
            errors.add(FieldError("incidentPurok", "Incident Purok is required"))
        }
        
        if (caseDetails.incidentDescription.isBlank()) {
            errors.add(FieldError("incidentDescription", "Incident Description is required"))
        } else if (caseDetails.incidentDescription.length < 10) {
            errors.add(FieldError("incidentDescription", "Incident description must be at least 10 characters"))
        }
        
        return errors
    }
    
    // Legacy validation methods for backward compatibility
    fun validateComplainantDataLegacy(complainant: ComplainantDetails): List<String> {
        return validateComplainantData(complainant).map { it.message }
    }

    fun validateRespondentDataLegacy(respondent: RespondentDetails): List<String> {
        return validateRespondentData(respondent).map { it.message }
    }

    fun validateCaseDataLegacy(caseDetails: CaseDetails): List<String> {
        return validateCaseData(caseDetails).map { it.message }
    }
}
