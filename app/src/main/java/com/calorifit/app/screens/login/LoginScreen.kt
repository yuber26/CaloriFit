package com.calorifit.app.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
// import androidx.compose.ui.platform.LocalUriHandler // No es estrictamente necesario para el LinkAnnotation si solo usamos el listener
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.calorifit.app.R
import com.calorifit.app.ui.theme.CaloriFitTheme
import com.calorifit.app.ui.theme.CalorifitBluePrimary
import com.calorifit.app.ui.theme.CalorifitGreenButtonAction
import com.calorifit.app.ui.theme.CalorifitGreenSecondary
import com.calorifit.app.ui.theme.CalorifitLightBackground
import com.calorifit.app.ui.theme.CalorifitTextPrincipal
import androidx.compose.ui.text.ExperimentalTextApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun LoginScreen(navController: NavHostController) {

    val gradientColors = listOf(
        CalorifitGreenSecondary,
        CalorifitLightBackground
    )

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(colors = gradientColors))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp)
                    .verticalScroll(rememberScrollState())
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(60.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_logo_calorifit),
                    contentDescription = "Logo de CaloriFit",
                    modifier = Modifier
                        .height(50.dp)
                        .padding(bottom = 24.dp)
                )

                Text(
                    text = "Inicia sesión con tu correo",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = CalorifitTextPrincipal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Accede a todas tus estadísticas y recomendaciones personalizadas",
                    style = MaterialTheme.typography.bodyMedium,
                    color = CalorifitTextPrincipal.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)
                    ) {
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Correo Electrónico") },
                            trailingIcon = {
                                Icon(
                                    Icons.Filled.Email,
                                    contentDescription = "Icono de correo"
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),
                            singleLine = true,
                            shape = MaterialTheme.shapes.medium
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Contraseña") },
                            leadingIcon = {
                                Icon(
                                    Icons.Filled.Lock,
                                    contentDescription = "Icono de contraseña"
                                )
                            },
                            trailingIcon = {
                                val image = if (passwordVisible)
                                    Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff
                                val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"

                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(imageVector = image, description)
                                }
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus() }
                            ),
                            singleLine = true,
                            shape = MaterialTheme.shapes.medium
                        )
                    }
                }

                // --- LINK ¿OLVIDASTE TU CONTRASEÑA? ---
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 40.dp) // Espacio aumentado antes del botón de Login
                ) {
                    Text(
                        text = "¿Olvidaste tu contraseña?",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                        color = CalorifitBluePrimary,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable { // Navega a la pantalla de olvidé contraseña
                                navController.navigate("forgot_password")
                                println("Clic en ¿Olvidaste tu contraseña?, navegando a forgot_password")
                            }
                            .padding(horizontal = 4.dp)
                    )
                }


                Button(
                    onClick = {
                        focusManager.clearFocus()
                        println("Botón Iniciar Sesión presionado. Email: $email, Pass: $password")
                        // TODO: Implementar lógica de Login
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CalorifitGreenButtonAction
                    )
                ) {
                    Text(
                        "Iniciar sesión",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                }

                val annotatedStringRegistrate = buildAnnotatedString {
                    append("¿No tienes cuenta? ")
                    pushLink(
                        LinkAnnotation.Clickable(
                            tag = "REGISTRATE_LINK",
                            linkInteractionListener = {
                                // Navegación a la pantalla de Registro (aún placeholder)
                                navController.navigate("register")
                                println("Navegando a la pantalla de Registro...")
                            }
                        )
                    )
                    withStyle(style = SpanStyle(color = CalorifitBluePrimary, fontWeight = FontWeight.Bold)) {
                        append("Regístrate")
                    }
                    pop()
                }

                Text(
                    text = annotatedStringRegistrate,
                    style = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center,
                        color = CalorifitTextPrincipal.copy(alpha = 0.8f)
                    ),
                    modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Protegemos tus datos según la Ley de Protección de Datos LATAM.",
                    style = MaterialTheme.typography.labelSmall,
                    color = CalorifitTextPrincipal.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp),
                    maxLines = 1
                )
            }
        }
    }
}


@Preview(showBackground = true, device = "id:pixel_7")
@Composable
fun LoginScreenPreview() {
    CaloriFitTheme {
        LoginScreen(navController = rememberNavController())
    }
}