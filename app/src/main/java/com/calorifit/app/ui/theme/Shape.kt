package com.calorifit.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// Definición de las formas para CaloriFit

val CaloriFitShapes = Shapes(
    // Usados para componentes pequeños como chips, o elementos dentro de cards.
    extraSmall = RoundedCornerShape(4.dp),
    // Comúnmente usado para componentes como botones pequeños o medianos, campos de texto.
    small = RoundedCornerShape(8.dp),
    // Usado para cards, diálogos, menús. (Tu "CalorieCard" y "Login Card")
    medium = RoundedCornerShape(12.dp), // Puedes ajustar este valor si quieres más o menos redondeo
    // Usado para componentes más grandes o modales.
    large = RoundedCornerShape(16.dp),
    // Para tu "botón principal con esquinas 2xl" y otros elementos que necesiten mucho redondeo.
    extraLarge = RoundedCornerShape(12.dp) // "2xl" podría ser 24.dp o incluso 28.dp o 32.dp.
    // Lo dejamos en 24.dp por ahora, puedes ajustarlo.
)