package com.calorifit.app // O el nombre de tu paquete raíz si es diferente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController // Importa NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.calorifit.app.screens.splash.SplashScreen // Importa tu SplashScreen
import com.calorifit.app.screens.welcome.WelcomeScreen // Importa tu WelcomeScreen
import com.calorifit.app.ui.theme.CaloriFitTheme
import com.calorifit.app.screens.login.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilitar Edge-to-Edge (pantalla completa)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CaloriFitTheme {
                // Creamos una instancia del NavController que recordará el estado de navegación
                val navController: NavHostController = rememberNavController()

                // Configuramos el NavHost, que es el contenedor donde se mostrarán nuestras pantallas
                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    composable(route = "splash") {
                        // ... (código del SplashScreen como antes)
                        SplashScreen(
                            onTimeout = {
                                navController.navigate("welcome") {
                                    popUpTo("splash") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable(route = "welcome") {
                        WelcomeScreen(navController = navController)
                    }

                    // NUEVA RUTA para la pantalla de Login por correo
                    composable(route = "login") {
                        LoginScreen(navController = navController)
                    }

                    // ... (futuras rutas)
                }

                    // En el futuro, aquí añadiremos más rutas para otras pantallas:
                    // composable(route = "onboarding") { /* OnboardingScreen(navController) */ }
                    // composable(route = "login") { /* LoginScreen(navController) */ }
                    // composable(route = "home") { /* HomeScreen(navController) */ }
                }
            }
        }

}