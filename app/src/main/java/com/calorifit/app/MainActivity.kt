package com.calorifit.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.calorifit.app.onboarding.OnboardingScreen
import com.calorifit.app.screens.forgotpassword.ForgotPasswordScreen
import com.calorifit.app.screens.login.LoginScreen
import com.calorifit.app.screens.register.RegisterScreen
import com.calorifit.app.screens.splash.SplashScreen
import com.calorifit.app.screens.welcome.WelcomeScreen
import com.calorifit.app.ui.theme.CaloriFitTheme
import dagger.hilt.android.AndroidEntryPoint // <-- 1. IMPORTA LA ANOTACIÓN

@AndroidEntryPoint // <-- 2. AÑADE LA ANOTACIÓN AQUÍ
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita el diseño Edge-to-Edge para que la app ocupe toda la pantalla
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CaloriFitTheme {
                val navController: NavHostController = rememberNavController()

                // NavHost es el componente que gestiona la navegación entre pantallas (Composables)
                NavHost(
                    navController = navController,
                    startDestination = "splash" // La primera pantalla que se muestra
                ) {
                    // Cada `composable` define una "ruta" o pantalla en la app.

                    composable(route = "splash") {
                        SplashScreen(
                            onTimeout = {
                                // Navega a "welcome" y elimina "splash" del historial de navegación
                                navController.navigate("welcome") {
                                    popUpTo("splash") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable(route = "welcome") {
                        WelcomeScreen(navController = navController)
                    }

                    composable(route = "login") {
                        LoginScreen(navController = navController)
                    }

                    composable(route = "register") {
                        RegisterScreen(navController = navController)
                    }

                    composable(route = "forgot_password") {
                        ForgotPasswordScreen(navController = navController)
                    }

                    // --- RUTA DE ONBOARDING ---
                    composable(route = "onboarding") {
                        OnboardingScreen(navController = navController)
                    }
                    // ------------------------------------
                }
            }
        }
    }
}