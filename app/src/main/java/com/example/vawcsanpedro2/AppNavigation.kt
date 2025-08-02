package com.example.vawcsanpedro2

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "onboarding") {

        composable("onboarding") {
            OnboardingScreen(onFinish = {
                navController.navigate("sign_in_guest") {
                    popUpTo("onboarding") { inclusive = true }
                }
            })
        }

        composable("sign_in_guest") {
            SignInGuestScreen(navController)
        }

        composable("landing") {
            LandingPage(navController)
        }

        composable("terms") {
            TermsAndConditionsScreen(navController)
        }

        composable("complaint_form") {
            ComplaintFormScreen(navController = navController)
        }
        composable("ra9262") {
            LandingPageRA9262(navController)
        }

        composable("ra9208") {
            LandingPageRA9208(navController)
        }

        composable("ra7877") {
            LandingPageRA7877(navController)
        }

    }
}