package com.example.vawcsanpedro2.backendmodel

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

object SecurityManager {
    private const val TAG = "SecurityManager"
    private const val ALGORITHM = "AES/GCM/NoPadding"
    private const val KEY_SIZE = 256
    private const val GCM_IV_LENGTH = 12
    private const val GCM_TAG_LENGTH = 16
    
    private lateinit var secretKey: SecretKeySpec
    private val secureRandom = SecureRandom()
    
    // Security validation patterns
    private val namePattern = Regex("^[a-zA-Z\\s\\-']{1,50}$")
    private val phonePattern = Regex("^[+]?[0-9\\s\\-()]{7,15}$")
    private val agePattern = Regex("^[0-9]{1,3}$")
    private val addressPattern = Regex("^[a-zA-Z0-9\\s\\-.,'()]{1,100}$")
    
    fun initialize(context: Context) {
        try {
            // Generate a cryptographically secure key
            val keyBytes = ByteArray(KEY_SIZE / 8)
            secureRandom.nextBytes(keyBytes)
            secretKey = SecretKeySpec(keyBytes, "AES")
            
            // Verify app integrity
            if (!verifyAppIntegrity(context)) {
                Log.e(TAG, "App integrity check failed")
                throw SecurityException("App integrity verification failed")
            }
            
            Log.d(TAG, "SecurityManager initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize SecurityManager", e)
            throw SecurityException("Security initialization failed", e)
        }
    }
    
    fun encrypt(data: String): String {
        if (data.isBlank()) return ""
        
        try {
            val iv = ByteArray(GCM_IV_LENGTH)
            secureRandom.nextBytes(iv)
            
            val cipher = Cipher.getInstance(ALGORITHM)
            val gcmSpec = GCMParameterSpec(GCM_TAG_LENGTH * 8, iv)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmSpec)
            
            val encrypted = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
            val combined = iv + encrypted
            
            return Base64.encodeToString(combined, Base64.NO_WRAP)
        } catch (e: Exception) {
            Log.e(TAG, "Encryption failed", e)
            throw SecurityException("Data encryption failed", e)
        }
    }
    
    fun decrypt(encryptedData: String): String {
        if (encryptedData.isBlank()) return ""
        
        try {
            val decoded = Base64.decode(encryptedData, Base64.NO_WRAP)
            val iv = decoded.copyOfRange(0, GCM_IV_LENGTH)
            val encrypted = decoded.copyOfRange(GCM_IV_LENGTH, decoded.size)
            
            val cipher = Cipher.getInstance(ALGORITHM)
            val gcmSpec = GCMParameterSpec(GCM_TAG_LENGTH * 8, iv)
            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmSpec)
            
            val decrypted = cipher.doFinal(encrypted)
            return String(decrypted, Charsets.UTF_8)
        } catch (e: Exception) {
            Log.e(TAG, "Decryption failed", e)
            throw SecurityException("Data decryption failed", e)
        }
    }
    
    // Input validation functions
    fun validateName(name: String): Boolean {
        return namePattern.matches(name.trim())
    }
    
    fun validatePhone(phone: String): Boolean {
        return phonePattern.matches(phone.trim())
    }
    
    fun validateAge(age: String): Boolean {
        return agePattern.matches(age.trim()) && age.toIntOrNull() in 1..120
    }
    
    fun validateAddress(address: String): Boolean {
        return addressPattern.matches(address.trim())
    }
    
    fun sanitizeInput(input: String): String {
        return input.trim()
            .replace(Regex("[<>\"']"), "") // Remove potential HTML/script tags
            .take(100) // Limit length
    }
    
    // App integrity verification
    private fun verifyAppIntegrity(context: Context): Boolean {
        return try {
            // Check if app is installed from Play Store (basic check)
            val installer = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                context.packageManager.getInstallSourceInfo(context.packageName).installingPackageName
            } else {
                @Suppress("DEPRECATION")
                context.packageManager.getInstallerPackageName(context.packageName)
            }
            
            // Check for debug mode
            val isDebug = (context.applicationInfo.flags and android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE) != 0
            
            // For production, you might want to enforce these checks
            // For now, we'll log them but not block
            Log.d(TAG, "Installer: $installer, Debug: $isDebug")
            
            true // Allow for development
        } catch (e: Exception) {
            Log.e(TAG, "Integrity check failed", e)
            false
        }
    }
    
    // Generate secure random ID
    fun generateSecureId(): String {
        val bytes = ByteArray(16)
        secureRandom.nextBytes(bytes)
        return Base64.encodeToString(bytes, Base64.NO_WRAP).take(12)
    }
    
    // Hash sensitive data for comparison (without storing original)
    fun hashData(data: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(data.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(hash, Base64.NO_WRAP)
    }
}
