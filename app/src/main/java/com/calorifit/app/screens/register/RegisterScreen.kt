package com.calorifit.app.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.calorifit.app.R
import com.calorifit.app.ui.theme.CaloriFitTheme
import com.calorifit.app.ui.theme.CalorifitBluePrimary
import com.calorifit.app.ui.theme.CalorifitGreenButtonAction
import com.calorifit.app.ui.theme.CalorifitGreenSecondary
import com.calorifit.app.ui.theme.CalorifitLightBackground
import com.calorifit.app.ui.theme.CalorifitTextPrincipal

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun RegisterScreen(navController: NavHostController) {

    val gradientColors = listOf(
        CalorifitGreenSecondary,
        CalorifitLightBackground
    )

    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var termsAccepted by rememberSaveable { mutableStateOf(false) }

    // NUEVO ESTADO para controlar el foco del campo de contraseña
    var isPasswordFocused by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    // Lógica de validación desglosada
    val isPasswordLongEnough = password.length >= 8
    val hasUpperCase = password.any { it.isUpperCase() }
    val hasNumber = password.any { it.isDigit() }
    val doPasswordsMatch = password == confirmPassword && password.isNotBlank()

    val isFormValid by remember(fullName, email, termsAccepted, isPasswordLongEnough, hasUpperCase, hasNumber, doPasswordsMatch) {
        derivedStateOf {
            fullName.isNotBlank() &&
                    email.isNotBlank() &&
                    termsAccepted &&
                    isPasswordLongEnough &&
                    hasUpperCase &&
                    hasNumber &&
                    doPasswordsMatch
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* No title needed */ },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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

                Text(
                    text = "Crea tu cuenta",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = CalorifitTextPrincipal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Empieza tu transformación con IA personalizada",
                    style = MaterialTheme.typography.bodyMedium,
                    color = CalorifitTextPrincipal.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // --- Campos de texto ---
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Nombre y Apellido") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Nombre") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Dirección de correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium
                )
                Spacer(modifier = Modifier.height(16.dp))

                // --- CAMPO DE CONTRASEÑA ---
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    // CAMBIO AQUÍ: Añadimos onFocusChanged para rastrear el foco
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            isPasswordFocused = focusState.isFocused
                        },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Contraseña") },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                    supportingText = {
                        // CAMBIO AQUÍ: Mostramos los requisitos solo si el campo tiene el foco
                        if (isPasswordFocused) {
                            Column {
                                PasswordRequirement(isMet = isPasswordLongEnough, text = "Mínimo 8 caracteres")
                                PasswordRequirement(isMet = hasUpperCase, text = "Al menos una mayúscula")
                                PasswordRequirement(isMet = hasNumber, text = "Al menos un número")
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))

                // --- CAMPO CONFIRMAR CONTRASEÑA ---
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirmar contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Confirmar contraseña") },
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                imageVector = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (confirmPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                            )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                    // CAMBIO AQUÍ: Lógica de error y texto de soporte
                    isError = confirmPassword.isNotBlank() && !doPasswordsMatch,
                    supportingText = {
                        if (confirmPassword.isNotBlank() && !doPasswordsMatch) {
                            Text(
                                text = "Las contraseñas no coinciden",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))

                val termsText = buildAnnotatedString {
                    append("Acepto los ")
                    pushLink(LinkAnnotation.Clickable("TERMS", linkInteractionListener = { /* TODO */ }))
                    withStyle(style = SpanStyle(color = CalorifitBluePrimary, fontWeight = FontWeight.SemiBold)) { append("Términos y Condiciones") }
                    pop()
                    append(" y la ")
                    pushLink(LinkAnnotation.Clickable("PRIVACY", linkInteractionListener = { /* TODO */ }))
                    withStyle(style = SpanStyle(color = CalorifitBluePrimary, fontWeight = FontWeight.SemiBold)) { append("Política de Privacidad") }
                    pop()
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = termsAccepted,
                        onCheckedChange = { termsAccepted = it }
                    )
                    Text(
                        text = termsText,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        println("Registrando: $fullName, $email")
                        // TODO: Lógica de Registro
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = CalorifitGreenButtonAction),
                    enabled = isFormValid
                ) {
                    Text(
                        "Crear cuenta",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                }
                val loginText = buildAnnotatedString {
                    append("¿Ya tienes una cuenta? ")
                    pushLink(LinkAnnotation.Clickable("LOGIN", linkInteractionListener = { navController.popBackStack() }))
                    withStyle(style = SpanStyle(color = CalorifitBluePrimary, fontWeight = FontWeight.Bold)) { append("Inicia sesión") }
                    pop()
                }
                Text(
                    text = loginText,
                    style = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    modifier = Modifier.padding(top = 24.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Tu información está protegida y solo se usará para personalizar tu experiencia.",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = CalorifitTextPrincipal.copy(alpha = 0.6f),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
fun PasswordRequirement(isMet: Boolean, text: String) {
    val (icon, color) = if (isMet) {
        Icons.Default.CheckCircle to Color(0xFF008000) // Un verde más explícito
    } else {
        Icons.Default.Cancel to MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = color,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_7")
@Composable
fun RegisterScreenPreview() {
    CaloriFitTheme {
        RegisterScreen(navController = rememberNavController())
    }
}