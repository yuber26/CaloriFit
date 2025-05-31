package com.calorifit.app.screens.login // O la ruta de tu paquete

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.calorifit.app.ui.theme.CaloriFitTheme

@Composable
fun LoginScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Pantalla de Login (Email/Contraseña)",
            style = MaterialTheme.typography.headlineMedium
        )
        // Aquí irá el diseño de la LoginScreen con campos de texto y botón
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    CaloriFitTheme {
        LoginScreen(navController = rememberNavController())
    }
}