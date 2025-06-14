package com.calorifit.app.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {

    // 1. ESTADO DE LOS DATOS DEL ONBOARDING
    // _state es privado y mutable, solo el ViewModel puede cambiarlo.
    private val _state = MutableStateFlow(OnboardingState())
    // state es público e inmutable, la UI solo puede leerlo y reaccionar a sus cambios.
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

    // 2. ESTADO DEL PASO ACTUAL
    // Controla qué pantalla del onboarding se está mostrando actualmente.
    private val _currentStep = MutableStateFlow<InitialOnboardingStep>(InitialOnboardingStep.GoalStep)
    val currentStep: StateFlow<InitialOnboardingStep> = _currentStep.asStateFlow()

    // 3. EVENTOS: FUNCIONES PARA ACTUALIZAR EL ESTADO DESDE LA UI

    fun updateGoal(goal: String) {
        _state.update { it.copy(goal = goal) }
    }

    fun updateWantsMealPlan(wantsPlan: Boolean) {
        _state.update { it.copy(wantsMealPlan = wantsPlan) }
    }

    fun updateGender(gender: String) {
        _state.update { it.copy(gender = gender) }
    }

    fun updateAge(age: Int) {
        _state.update { it.copy(age = age) }
    }

    fun updateHeight(cm: Int) {
        _state.update { it.copy(heightInCm = cm) }
    }

    fun updateWeight(kg: Float) {
        _state.update { it.copy(weightInKg = kg) }
    }

    fun updateActivityLevel(level: String) {
        _state.update { it.copy(activityLevel = level) }
    }

    fun updateDoesStrengthTraining(doesTraining: Boolean) {
        _state.update { it.copy(doesStrengthTraining = doesTraining) }
    }

    fun updateTargetWeight(kg: Float) {
        _state.update { it.copy(targetWeightInKg = kg) }
    }

    fun updatePace(pace: String) {
        _state.update { it.copy(pace = pace) }
    }

    // 4. LÓGICA DE NAVEGACIÓN

    /**
     * Avanza al siguiente paso del onboarding basado en el paso actual.
     * Esta función actúa como una máquina de estados.
     */
    fun onNextClicked() {
        when (_currentStep.value) {
            InitialOnboardingStep.GoalStep -> _currentStep.value = InitialOnboardingStep.HowToAchieveGoalStep
            InitialOnboardingStep.HowToAchieveGoalStep -> _currentStep.value = InitialOnboardingStep.AboutYouStep
            InitialOnboardingStep.AboutYouStep -> _currentStep.value = InitialOnboardingStep.ActivityLevelStep
            InitialOnboardingStep.ActivityLevelStep -> _currentStep.value = InitialOnboardingStep.StrengthTrainingStep
            InitialOnboardingStep.StrengthTrainingStep -> _currentStep.value = InitialOnboardingStep.GoalPersonalizationStep
            InitialOnboardingStep.GoalPersonalizationStep -> _currentStep.value = InitialOnboardingStep.SummaryStep
            InitialOnboardingStep.SummaryStep -> _currentStep.value = InitialOnboardingStep.NotificationPermissionStep
            InitialOnboardingStep.NotificationPermissionStep -> _currentStep.value = InitialOnboardingStep.AuthGateStep
            InitialOnboardingStep.AuthGateStep -> {
                // El flujo inicial ha terminado. Aquí se navegaría a la pantalla de Home/Auth.
                // Esta lógica la manejaremos en la UI.
            }
        }
    }

    /**
     * Retrocede al paso anterior del onboarding.
     */
    fun onPreviousClicked() {
        when (_currentStep.value) {
            InitialOnboardingStep.GoalStep -> {
                // Es el primer paso, no hay paso anterior.
                // La UI podría interpretar esto como cerrar el onboarding.
            }
            InitialOnboardingStep.HowToAchieveGoalStep -> _currentStep.value = InitialOnboardingStep.GoalStep
            InitialOnboardingStep.AboutYouStep -> _currentStep.value = InitialOnboardingStep.HowToAchieveGoalStep
            InitialOnboardingStep.ActivityLevelStep -> _currentStep.value = InitialOnboardingStep.AboutYouStep
            InitialOnboardingStep.StrengthTrainingStep -> _currentStep.value = InitialOnboardingStep.ActivityLevelStep
            InitialOnboardingStep.GoalPersonalizationStep -> _currentStep.value = InitialOnboardingStep.StrengthTrainingStep
            InitialOnboardingStep.SummaryStep -> _currentStep.value = InitialOnboardingStep.GoalPersonalizationStep
            InitialOnboardingStep.NotificationPermissionStep -> _currentStep.value = InitialOnboardingStep.SummaryStep
            InitialOnboardingStep.AuthGateStep -> _currentStep.value = InitialOnboardingStep.NotificationPermissionStep
        }
    }
}