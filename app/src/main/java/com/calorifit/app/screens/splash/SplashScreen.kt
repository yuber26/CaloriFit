package com.calorifit.app.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.calorifit.app.R // Importa R para acceder a los recursos drawable
import com.calorifit.app.ui.theme.CaloriFitTheme
import com.calorifit.app.ui.theme.CalorifitGreenSecondary // El verde agua/claro
import com.calorifit.app.ui.theme.CalorifitLightBackground // El blanco azulado/muy claro
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) { // onTimeout es una función para navegar después del delay

    // Para el efecto de Fade-in
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1000), // Duración del fade-in: 1 segundo
        label = "SplashAlphaAnimation"
    )

    // Efecto para cambiar el color de la barra de estado y comportamiento
    // Esto es específico para esta pantalla.
    val view = LocalView.current
    if (!view.isInEditMode) { // No ejecutar en modo Preview
        SideEffect {
            val window = (view.context as android.app.Activity).window
            // Hacemos la barra de estado transparente para que el degradado se vea detrás
            window.statusBarColor = Color.Transparent.toArgb()
            // Ponemos los iconos de la barra de estado en oscuro, ya que nuestro fondo es claro
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    // Lanzamos el efecto de delay y navegación solo una vez
    LaunchedEffect(key1 = true) {
        startAnimation = true // Inicia la animación de fade-in
        delay(2500L) // Espera 2.5 segundos
        onTimeout() // Llama a la función para navegar a la siguiente pantalla
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        CalorifitLightBackground, // Color de arriba (#F4FAFF)
                        CalorifitGreenSecondary   // Color de abajo (#E9FCEE)
                    )
                )
            )
            .alpha(alphaAnim), // Aplicamos la animación de fade-in al Box entero
        contentAlignment = Alignment.Center
    ) {
        // --- OPCIÓN 1: Si tienes un logo en drawable ---
        // Reemplaza 'R.drawable.ic_logo_calorifit' con el nombre real de tu archivo de logo
        Image(
            painter = painterResource(id = R.drawable.ic_logo_calorifit),
            contentDescription = "Logo CaloriFit",
            modifier = Modifier.size(220.dp) // Ajusta el tamaño según tu logo
        )

        // --- OPCIÓN 2: Si NO tienes un logo y quieres usar texto como en Splash_2 (animado).jpg ---
        // Comenta la Image de arriba y descomenta este Text de abajo:
        /*
        Row {
            Text(
                text = "CALORI",
                fontSize = 40.sp, // Tamaño grande para el splash
                fontWeight = FontWeight.Bold,
                color = CalorifitBluePrimary
            )
            Text(
                text = "FIT",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary // Usando el verde secundario del tema
                                                           // que definimos como CalorifitGreenSecondary.
                                                           // O puedes usar un verde más oscuro si lo prefieres
                                                           // para el texto.
            )
        }
        */
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    CaloriFitTheme {
        // Para la preview, es útil simular el efecto onTimeout,
        // aunque aquí no navegará realmente.
        SplashScreen(onTimeout = {})
    }
}

// Placeholder para el recurso del logo si aún no lo tienes,
// crea un archivo ic_logo_calorifit_placeholder.xml en res/drawable con un vector simple
// o usa cualquier otra imagen que tengas ahí para que compile. Ejemplo:
/*
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="24"
    android:viewportHeight="24">
  <path
      android:fillColor="@android:color/darker_gray"
      android:pathData="M12,2C6.48,2 2,6.48 2,12s4.48,10 10,10 10,-4.48 10,-10S17.52,2 12,2z"/>
</vector>
*/