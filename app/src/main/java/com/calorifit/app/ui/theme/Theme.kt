package com.calorifit.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
// import androidx.compose.material3.darkColorScheme // Descomentar si se implementa un tema oscuro dedicado
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Definición del esquema de colores claros para CaloriFit
// Aquí mapeamos los colores que creamos en Color.kt a los roles de Material 3
private val CaloriFitLightColorScheme = lightColorScheme(
    primary = CalorifitBluePrimary,
    onPrimary = CalorifitOnPrimary,
    secondary = CalorifitGreenSecondary, // Este es un verde muy claro, ideal para detalles o fondos sutiles.
    // Si necesitas un secundario más fuerte para acentos,
    // podríamos considerar CalorifitYellowAccent aquí.
    onSecondary = CalorifitOnSecondary,
    background = CalorifitLightBackground,
    onBackground = CalorifitTextPrincipal,
    surface = CalorifitLightBackground, // Usamos el Fondo Base para superficies como cards y pantallas.
    // Podría ser CalorifitWhite si prefieres cards blancas sobre un fondo ligeramente coloreado.
    onSurface = CalorifitTextPrincipal,
    error = CalorifitError,
    onError = CalorifitWhite, // Generalmente el texto sobre un color de error es blanco.

    // Puedes definir otros colores del esquema si los necesitas:
    // primaryContainer = ...,
    // onPrimaryContainer = ...,
    // secondaryContainer = CalorifitGreenSecondary, // El verde claro podría encajar bien aquí.
    // onSecondaryContainer = ...,
    // tertiary = CalorifitYellowAccent, // El amarillo de acento podría ser un buen terciario.
    // onTertiary = ...,
    // surfaceVariant = ...,
    // onSurfaceVariant = ...,
    // outline = ...,
)

/*
// Si en el futuro quieres un tema oscuro dedicado:
private val CaloriFitDarkColorScheme = darkColorScheme(
    primary = CalorifitBluePrimary, // O una variante más oscura/clara del azul para modo oscuro
    onPrimary = CalorifitOnPrimary,
    secondary = CalorifitGreenSecondary, // O una variante adecuada para modo oscuro
    onSecondary = CalorifitOnSecondary,
    background = Color(0xFF121212), // Un gris oscuro común para fondos en modo oscuro
    onBackground = CalorifitWhite,
    surface = Color(0xFF1E1E1E), // Un gris ligeramente más claro para superficies
    onSurface = CalorifitWhite,
    error = CalorifitError,
    onError = CalorifitWhite
    // ... y así con el resto de colores
)
*/

@Composable
fun CaloriFitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Por ahora, dejaremos que el sistema decida si el modo oscuro está activo,
    // pero nuestro esquema de colores principal será el claro.
    // Podrías forzarlo a 'false' si solo quieres tema claro.
    // Dynamic color is available on Android 12+
    // dynamicColor: Boolean = true, // El color dinámico toma colores del wallpaper del usuario.
    // Considera si esto encaja con tu branding estricto.
    // Por ahora lo dejaremos en 'false' (valor por defecto si no se especifica)
    // para usar nuestros colores de marca.
    content: @Composable () -> Unit
) {
    // Por ahora, siempre usaremos el CaloriFitLightColorScheme.
    // Si implementas CaloriFitDarkColorScheme, puedes usar la lógica de 'darkTheme'
    // val colorScheme = if (darkTheme) CaloriFitDarkColorScheme else CaloriFitLightColorScheme
    val colorScheme = CaloriFitLightColorScheme

    // Esto es para controlar la apariencia de las barras de sistema (status bar, navigation bar)
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Color de la barra de estado (arriba)
            window.statusBarColor = colorScheme.primary.toArgb() // Puedes cambiarlo a background.toArgb() si prefieres
            // Iconos de la barra de estado (claros u oscuros)
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
            // Si usas un color de fondo claro para la status bar, y quieres iconos oscuros:
            // window.statusBarColor = colorScheme.background.toArgb()
            // WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme // true para iconos oscuros
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CaloriFitTypography,
        shapes = CaloriFitShapes,
        content = content
    )
}