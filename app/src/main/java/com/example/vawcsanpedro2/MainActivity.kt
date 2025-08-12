package com.example.vawcsanpedro2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.vawcsanpedro2.ui.theme.VawcSanPedro2Theme
import com.example.vawcsanpedro2.ui.theme.*
import kotlinx.coroutines.delay
import com.example.vawcsanpedro2.backendmodel.SecurityManager
import com.google.firebase.FirebaseApp
import android.util.Log
import android.content.res.Configuration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase
        try {
            FirebaseApp.initializeApp(this)
            Log.d("MainActivity", "Firebase initialized successfully")
        } catch (e: Exception) {
            Log.e("MainActivity", "Firebase initialization failed", e)
        }

        // Initialize enhanced security manager - this must complete before UI setup
        try {
            SecurityManager.initialize(this)
            Log.d("MainActivity", "SecurityManager initialized successfully")
            
            // Verify initialization was successful
            if (!SecurityManager.isInitialized()) {
                throw SecurityException("SecurityManager initialization verification failed")
            }
        } catch (e: Exception) {
            // Security initialization failed - this is critical
            Log.e("MainActivity", "Security initialization failed", e)
            // Show error and exit or handle gracefully
            // For now, we'll continue but this might cause issues
        }

        enableEdgeToEdge()
        setContent {
            VawcSanPedro2Theme {
                MainScreen() // ✅ Use MainScreen here
            }
        }
    }
}

@Composable
fun MainScreen() {
    var showSplash by remember { mutableStateOf(true) }
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        delay(3000) // 3-second splash screen delay
        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
    } else {
        VawcSanPedro2Theme {
            AppNavigation(navController = navController)
        }
    }
}

@Composable
fun SplashScreen() {
    val isDarkTheme = isSystemInDarkTheme()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkTheme) Color.Black else Color.White)
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sanpedro1),
            contentDescription = "Barangay San Pedro Logo",
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "BARANGAY SAN PEDRO\nPAGADIAN CITY",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = if (isDarkTheme) PrimaryPink else Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    VawcSanPedro2Theme {
        SplashScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    VawcSanPedro2Theme {
        SplashScreen()
    }
}