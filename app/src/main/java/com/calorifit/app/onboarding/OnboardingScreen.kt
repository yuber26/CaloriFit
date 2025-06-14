package com.calorifit.app.onboarding

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.calorifit.app.R
import com.calorifit.app.onboarding.composables.SelectionCard
import com.calorifit.app.ui.theme.CalorifitYellowAccent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    navController: NavHostController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    // Por ahora, solo nos concentraremos en una pantalla,
    // pero dejamos la estructura lista para el futuro.
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background, // Usaremos el fondo del tema oscuro
        topBar = {
            TopAppBar(
                title = { /* Vacío por ahora */ },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: navController.popBackStack() */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent, navigationIconContentColor = Color.White)
            )
        },
        bottomBar = {
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CalorifitYellowAccent),
                shape = MaterialTheme.shapes.medium,
                enabled = uiState.mainGoal != null
            ) {
                Text("Continuar", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    ) { paddingValues ->
        GoalScreenContent(
            modifier = Modifier.padding(paddingValues),
            selectedGoal = uiState.mainGoal,
            onGoalSelected = { viewModel.updateMainGoal(it) } // Necesitaremos crear esta función
        )
    }
}


@Composable
private fun GoalScreenContent(
    modifier: Modifier = Modifier,
    selectedGoal: String?,
    onGoalSelected: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_logo_calorifit), // Reemplaza con tu ícono de objetivo
            contentDescription = "Objetivo",
            tint = CalorifitYellowAccent,
            modifier = Modifier
                .size(64.dp)
                .background(Color.DarkGray.copy(alpha = 0.5f), shape = MaterialTheme.shapes.extraLarge)
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "¿Cuál es tu objetivo?",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Calcularemos tus calorías necesarias para lograrlo",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(48.dp))

        // Opciones
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            val goalOptions = listOf(
                "Perder Grasa" to "Optimiza la pérdida de peso y conserva tu masa muscular",
                "Ganar Músculo" to "Incrementa tu peso y hazte más fuerte",
                "Mantener Peso" to "Mantén tu peso estable y busca la recomposición corporal"
            )
            goalOptions.forEach { (title, subtitle) ->
                SelectionCard(
                    title = title,
                    subtitle = subtitle,
                    icon = painterResource(id = R.drawable.ic_logo_calorifit), // Reemplaza con íconos relevantes
                    isSelected = selectedGoal == title,
                    onClick = { onGoalSelected(title) }
                )
            }
        }
    }
}
