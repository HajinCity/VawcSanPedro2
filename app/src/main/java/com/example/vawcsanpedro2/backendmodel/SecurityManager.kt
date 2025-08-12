package com.example.vawcsanpedro2.backendmodel

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import android.util.Log
import java.util.Properties
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
    
    // Encryption keys loaded from local.properties
    private lateinit var secretKey: SecretKeySpec
    private lateinit var fixedIV: ByteArray
    private val secureRandom = SecureRandom()
    
    // Security validation patterns
    private val namePattern = Regex("^[a-zA-Z\\s\\-']{1,50}$")
    private val phonePattern = Regex("^[+]?[0-9\\s\\-()]{7,15}$")
    private val agePattern = Regex("^[0-9]{1,3}$")
    private val addressPattern = Regex("^[a-zA-Z0-9\\s\\-.,'()]{1,100}$")
    
    // Check if SecurityManager is initialized
    fun isInitialized(): Boolean {
        return ::secretKey.isInitialized && ::fixedIV.isInitialized
    }
    
    // Load encryption keys from app resources
    private fun loadEncryptionKeys(context: Context): Pair<String, String> {
        return try {
            Log.d(TAG, "Starting to load encryption keys from app resources")
            
            // Try to load from assets first
            try {
                val assetManager = context.assets
                val inputStream = assetManager.open("encryption_keys.properties")
                val properties = Properties()
                inputStream.use { input ->
                    properties.load(input)
                }
                
                val secretKey = properties.getProperty("ENCRYPTION_SECRET_KEY")
                val initVector = properties.getProperty("ENCRYPTION_INIT_VECTOR")
                
                if (!secretKey.isNullOrBlank() && !initVector.isNullOrBlank()) {
                    Log.d(TAG, "Successfully loaded encryption keys from assets")
                    return Pair(secretKey, initVector)
                }
            } catch (e: Exception) {
                Log.d(TAG, "Could not load from assets: ${e.message}")
            }
            
            // Try to load from raw resources
            try {
                val inputStream = context.resources.openRawResource(
                    context.resources.getIdentifier("encryption_keys", "raw", context.packageName)
                )
                val properties = Properties()
                inputStream.use { input ->
                    properties.load(input)
                }
                
                val secretKey = properties.getProperty("ENCRYPTION_SECRET_KEY")
                val initVector = properties.getProperty("ENCRYPTION_INIT_VECTOR")
                
                if (!secretKey.isNullOrBlank() && !initVector.isNullOrBlank()) {
                    Log.d(TAG, "Successfully loaded encryption keys from raw resources")
                    return Pair(secretKey, initVector)
                }
            } catch (e: Exception) {
                Log.d(TAG, "Could not load from raw resources: ${e.message}")
            }
            
            // Fallback to hardcoded keys for development
            Log.w(TAG, "Using fallback hardcoded keys for development")
            val fallbackSecretKey = "7GJyMt/KUcpoQ/CZY4LsOiCXwEJDtO0xd50UkKRa2TY="
            val fallbackIV = "ccJqstjGLOQg4+zP"
            
            Log.d(TAG, "Fallback keys loaded successfully")
            Pair(fallbackSecretKey, fallbackIV)
            
        } catch (e: Exception) {
            Log.e(TAG, "Failed to load encryption keys", e)
            
            // Final fallback to hardcoded keys
            Log.w(TAG, "Using final fallback hardcoded keys")
            val fallbackSecretKey = "7GJyMt/KUcpoQ/CZY4LsOiCXwEJDtO0xd50UkKRa2TY="
            val fallbackIV = "ccJqstjGLOQg4+zP"
            
            Pair(fallbackSecretKey, fallbackIV)
        }
    }
    
    fun initialize(context: Context) {
        try {
            // Load encryption keys from local.properties
            val (secretKeyBase64, ivBase64) = loadEncryptionKeys(context)
            
            // Use loaded secret key
            val keyBytes = Base64.decode(secretKeyBase64, Base64.NO_WRAP)
            secretKey = SecretKeySpec(keyBytes, "AES")
            
            // Use loaded IV
            fixedIV = Base64.decode(ivBase64, Base64.NO_WRAP)
            
            // Verify app integrity
            if (!verifyAppIntegrity(context)) {
                Log.e(TAG, "App integrity check failed")
                throw SecurityException("App integrity verification failed")
            }
            
            Log.d(TAG, "SecurityManager initialized successfully with keys from local.properties")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize SecurityManager", e)
            throw SecurityException("Security initialization failed", e)
        }
    }
    
    fun encrypt(data: String): String {
        if (data.isBlank()) return ""
        
        // Check if SecurityManager is initialized
        if (!isInitialized()) {
            Log.e(TAG, "SecurityManager not initialized. Call initialize() first.")
            throw SecurityException("SecurityManager not initialized. Call initialize() first.")
        }
        
        try {
            val cipher = Cipher.getInstance(ALGORITHM)
            val gcmSpec = GCMParameterSpec(GCM_TAG_LENGTH * 8, fixedIV)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmSpec)
            
            val encrypted = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
            
            // Return only the encrypted data (IV is fixed and known)
            return Base64.encodeToString(encrypted, Base64.NO_WRAP)
        } catch (e: Exception) {
            Log.e(TAG, "Encryption failed", e)
            throw SecurityException("Data encryption failed", e)
        }
    }
    
    fun decrypt(encryptedData: String): String {
        if (encryptedData.isBlank()) return ""
        
        // Check if SecurityManager is initialized
        if (!isInitialized()) {
            Log.e(TAG, "SecurityManager not initialized. Call initialize() first.")
            throw SecurityException("SecurityManager not initialized. Call initialize() first.")
        }
        
        try {
            val encrypted = Base64.decode(encryptedData, Base64.NO_WRAP)
            
            val cipher = Cipher.getInstance(ALGORITHM)
            val gcmSpec = GCMParameterSpec(GCM_TAG_LENGTH * 8, fixedIV)
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
