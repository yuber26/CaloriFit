package com.calorifit.app.ui.theme

import androidx.compose.ui.graphics.Color

// Colores Principales de la App CaloriFit
// Estos nombres son descriptivos y los usaremos luego en Theme.kt

// Primario: Usado para botones principales, highlights, links importantes.
val CalorifitBluePrimary = Color(0xFF0051FF) // Azul [cite: 12, 36]

// Secundario: Usado para fondos claros, detalles y elementos menos prominentes.
val CalorifitGreenSecondary = Color(0xFFE9FCEE) // Verde agua/claro [cite: 12, 36]

// Fondo Base: Usado como color de fondo general para pantallas y cards.
val CalorifitLightBackground = Color(0xFFF4FAFF) // Blanco azulado/muy claro [cite: 12, 36]

// Texto Principal: Para títulos y texto importante.
val CalorifitTextPrincipal = Color(0xFF181818) // Gris oscuro/Negro [cite: 12, 36]

// Blanco: Usado para fondos de cards específicas, campos de input, y texto sobre fondos oscuros.
val CalorifitWhite = Color(0xFFFFFFFF) // Blanco puro [cite: 12, 36]

// Acento: Para estados activos, indicadores de éxito, o elementos que necesitan un realce especial (opcional).
val CalorifitYellowAccent = Color(0xFFFFD600) // Amarillo [cite: 12, 36]

// Colores adicionales que podrían ser útiles para Material 3 (especialmente para el esquema de colores)
// Puedes añadir más según necesites, por ejemplo, para errores, contenedores, etc.
val CalorifitError = Color(0xFFB00020) // Un color de error estándar
val CalorifitOnPrimary = Color.White // Texto/iconos sobre el color primario (Azul)
val CalorifitOnSecondary = Color.Black // Texto/iconos sobre el color secundario (Verde claro)
val CalorifitOnBackground = Color.Black // Texto/iconos sobre el color de fondo base
val CalorifitOnSurface = Color.Black // Texto/iconos sobre "superficies" (cards, sheets)
val CalorifitSurface = Color.White // Color de las superficies como cards
// Si quieres usar el Fondo Base para las superficies, puedes poner:
// val com.calorifit.app.ui.theme.getCalorifitSurface = com.calorifit.app.ui.theme.getCalorifitLightBackground

val CalorifitGreenButtonAction = Color(0xFF3AC444) // Color Boton WelcomeScreen

val FacebookBlue = Color(0xFF1877F2) // Azul Facebook