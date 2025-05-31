package com.calorifit.app.screens.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.calorifit.app.R
import com.calorifit.app.ui.theme.CaloriFitTheme
import com.calorifit.app.ui.theme.CalorifitBluePrimary
import com.calorifit.app.ui.theme.CalorifitGreenButtonAction
import com.calorifit.app.ui.theme.CalorifitGreenSecondary
import com.calorifit.app.ui.theme.CalorifitLightBackground
import com.calorifit.app.ui.theme.CalorifitTextPrincipal
import com.calorifit.app.ui.theme.FacebookBlue // <-- IMPORTA EL NUEVO COLOR
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun WelcomeScreen(navController: NavHostController) {

    val view = LocalView.current
    val isAppearanceLightStatusBars = true

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as android.app.Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isAppearanceLightStatusBars
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        CalorifitLightBackground,
                        CalorifitGreenSecondary
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 35.dp)
                .padding(top = 25.dp, bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text( "Un cuerpo fit empieza\npor tus decisiones", style = MaterialTheme.typography.headlineMedium.copy(fontSize = 28.sp), color = CalorifitTextPrincipal, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(16.dp))
            Text( "Descubre tu mejor versión con IA", style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp), color = CalorifitTextPrincipal, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(48.dp))
            Image( painter = painterResource(id = R.drawable.ic_logo_calorifit), contentDescription = "Logo CaloriFit", modifier = Modifier.height(50.dp))
            Spacer(modifier = Modifier.height(32.dp))
            Image( painter = painterResource(id = R.drawable.img_healthy_bowl), contentDescription = "Plato de comida saludable", modifier = Modifier.size(400.dp).padding(vertical = 10.dp), contentScale = ContentScale.Fit)
            Spacer(modifier = Modifier.weight(0.2f))

            Button(
                onClick = {
                    println("Botón Comencemos presionado! Navegando a Onboarding...")
                    // navController.navigate("onboarding")
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors( containerColor = CalorifitGreenButtonAction, contentColor = Color.White)
            ) {
                Text( "Comencemos 👉", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            }

            Spacer(modifier = Modifier.height(24.dp))

            val annotatedLoginString = buildAnnotatedString {
                append("¿Ya tienes una cuenta? ")
                pushLink(LinkAnnotation.Clickable(tag = "LOGIN_ACTION", linkInteractionListener = {
                    println("Link 'Inicia sesión' (LinkAnnotation) presionado!")
                    scope.launch { showBottomSheet = true }
                }))
                withStyle(style = SpanStyle(color = CalorifitBluePrimary, fontWeight = FontWeight.Bold)) {
                    append("Inicia sesión")
                }
                pop()
            }
            Text(
                text = annotatedLoginString,
                style = MaterialTheme.typography.bodyMedium.copy(color = CalorifitTextPrincipal, textAlign = TextAlign.Center)
            )
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            containerColor = MaterialTheme.colorScheme.surface,
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp, top = 24.dp, bottom = 48.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Ya no tenemos el handle explícito
                Text(
                    "Elige un método para iniciar sesión",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // 1. Botón Continuar con Google
                Button(
                    onClick = {
                        println("Login con Google presionado")
                        scope.launch { sheetState.hide() }.invokeOnCompletion { if (!sheetState.isVisible) showBottomSheet = false }
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp).padding(vertical = 7.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.DarkGray),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo_google_g),
                        contentDescription = "Google logo",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Continuar con Google")
                }

                // 2. Botón Continuar con Facebook (NUEVO)
                Button(
                    onClick = {
                        println("Login con Facebook presionado")
                        scope.launch { sheetState.hide() }.invokeOnCompletion { if (!sheetState.isVisible) showBottomSheet = false }
                        // Lógica de Firebase Auth para Facebook aquí
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp).padding(vertical = 7.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = FacebookBlue, // Color azul de Facebook
                        contentColor = Color.White     // Texto blanco
                    )
                ) {

                    Image(
                         painter = painterResource(id = R.drawable.ic_logo_facebook_f),
                         contentDescription = "Facebook logo",
                         modifier = Modifier.size(24.dp) // Ajusta el tamaño
                     )
                     Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Continuar con Facebook") // Por ahora solo texto
                }

                // 3. Botón Continuar con Correo (Estilo Cambiado)
                Button(
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                                navController.navigate("login")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp).padding(vertical = 7.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,    // CAMBIO: Fondo blanco
                        contentColor = Color.Black       // CAMBIO: Texto e icono (por defecto) negros
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline) // CAMBIO: Borde similar al de Google
                ) {
                    Icon(
                        Icons.Filled.Email,
                        contentDescription = "Correo"
                        // El tint del icono será Color.Black por el contentColor del botón
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Continuar con Correo")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

// Helper
fun Color.isLight() = (red * 0.299 + green * 0.587 + blue * 0.114) > 0.5

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun WelcomeScreenPreview() {
    CaloriFitTheme {
        WelcomeScreen(navController = rememberNavController())
    }
}