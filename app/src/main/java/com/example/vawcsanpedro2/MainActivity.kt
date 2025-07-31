package com.example.vawcsanpedro2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.vawcsanpedro2.ui.theme.VawcSanPedro2Theme
import kotlinx.coroutines.delay
import com.example.vawcsanpedro2.BuildConfig
import com.example.vawcsanpedro2.backendmodel.EncryptionTransit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EncryptionTransit.init(
            BuildConfig.ENCRYPTION_SECRET_KEY,
            BuildConfig.ENCRYPTION_INIT_VECTOR
        )



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
        AppNavigation(navController = navController) // ✅ Navigation happens after splash
    }
}

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
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
            color = Color.Black
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
