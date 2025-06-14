package com.calorifit.app.screens.forgotpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun ForgotPasswordScreen(navController: NavHostController) {

    val gradientColors = listOf(
        CalorifitGreenSecondary,
        CalorifitLightBackground
    )

    var email by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    var showConfirmationDialog by remember { mutableStateOf(false) }
    var showResendSection by remember { mutableStateOf(false) }
    var countdownSeconds by remember { mutableIntStateOf(60) }
    var isResendEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = showResendSection, key2 = isResendEnabled) {
        if (showResendSection && !isResendEnabled) {
            countdownSeconds = 60
            while (countdownSeconds > 0) {
                delay(1000L)
                countdownSeconds--
            }
            isResendEnabled = true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* No title needed */ },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = CalorifitTextPrincipal
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
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
                Spacer(modifier = Modifier.height(24.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_logo_calorifit),
                    contentDescription = "Logo de CaloriFit",
                    modifier = Modifier
                        .height(50.dp)
                        .padding(bottom = 32.dp)
                )

                Text(
                    text = "Restablecer Contraseña",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = CalorifitTextPrincipal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Ingresa el correo electrónico asociado a tu cuenta y te enviaremos un enlace para restablecer tu contraseña.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = CalorifitTextPrincipal.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Correo Electrónico") },
                    trailingIcon = {
                        Icon(Icons.Filled.Email, "Icono de correo")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                    enabled = !showResendSection
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        focusManager.clearFocus()
                        if (email.isNotBlank()) {
                            println("Solicitar restablecimiento para: $email.")
                            showConfirmationDialog = true
                            if (!showResendSection) {
                                showResendSection = true
                                isResendEnabled = false
                            }
                        } else {
                            println("Email no puede estar vacío")
                            // TODO: Mostrar error de email vacío
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = CalorifitGreenButtonAction),
                    enabled = !showResendSection
                ) {
                    Text(
                        "Enviar Enlace",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                }

                if (showResendSection) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = if (!isResendEnabled) {
                            "Reenviar en $countdownSeconds s."
                        } else {
                            "¿No recibiste el correo?"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = CalorifitTextPrincipal.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(
                        onClick = {
                            if (isResendEnabled) {
                                println("Reenviando correo a: $email")
                                showConfirmationDialog = true
                                isResendEnabled = false
                                countdownSeconds = 60
                            }
                        },
                        enabled = isResendEnabled
                    ) {
                        Text(
                            "Reenviar Correo",
                            fontWeight = if (isResendEnabled) FontWeight.Bold else FontWeight.Normal,
                            color = if (isResendEnabled) CalorifitBluePrimary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                        )
                    }
                }

                // El Spacer con weight(1f) asegura que cualquier contenido
                // restante de la pantalla esté vacío, empujando todo hacia arriba.
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }

    if (showConfirmationDialog) {
        AlertDialog(
            onDismissRequest = {
                showConfirmationDialog = false
            },
            title = { Text("¡Revisa tu Correo!") },
            text = {
                Text("Si la dirección de correo electrónico que ingresaste está asociada con una cuenta CaloriFit, hemos enviado un mensaje con las instrucciones para restablecer tu contraseña. Por favor, revisa tu bandeja de entrada y la carpeta de spam.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showConfirmationDialog = false
                    }
                ) {
                    Text("OK")
                }
            },
            icon = { Icon(Icons.Filled.Email, contentDescription = "Icono de correo enviado", tint = MaterialTheme.colorScheme.primary) }
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_7")
@Composable
fun ForgotPasswordScreenPreview() {
    CaloriFitTheme {
        ForgotPasswordScreen(navController = rememberNavController())
    }
}