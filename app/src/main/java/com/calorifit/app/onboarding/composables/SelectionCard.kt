package com.calorifit.app.onboarding.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SelectionCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    icon: Painter,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // Animaciones para una transición de selección suave
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface,
        label = "CardBackgroundColor"
    )
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) CalorifitYellowAccent else Color.DarkGray,
        label = "CardBorderColor"
    )
    val iconBackgroundColor by animateColorAsState(
        targetValue = if (isSelected) CalorifitYellowAccent else Color.DarkGray.copy(alpha = 0.5f),
        label = "IconBackgroundColor"
    )
    val iconTintColor by animateColorAsState(
        targetValue = if (isSelected) Color.Black else Color.White,
        label = "IconTintColor"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(backgroundColor)
            .border(1.dp, borderColor, MaterialTheme.shapes.large)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Círculo del icono
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(MaterialTheme.shapes.large)
                .background(iconBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = title,
                tint = iconTintColor,
                modifier = Modifier.size(24.dp)
            )
        }
        // Columna para textos
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}