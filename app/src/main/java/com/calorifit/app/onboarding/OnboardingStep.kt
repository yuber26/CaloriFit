package com.calorifit.app.onboarding

/**
 * Define los pasos posibles dentro del flujo de Onboarding Inicial.
 * Al ser una clase sellada, nos aseguramos de que solo estos pasos pueden existir en este flujo.
 */
sealed class InitialOnboardingStep {
    data object GoalStep : InitialOnboardingStep()
    data object HowToAchieveGoalStep : InitialOnboardingStep()
    data object AboutYouStep : InitialOnboardingStep()
    data object ActivityLevelStep : InitialOnboardingStep()
    data object StrengthTrainingStep : InitialOnboardingStep()
    data object GoalPersonalizationStep : InitialOnboardingStep()
    data object SummaryStep : InitialOnboardingStep()
    data object NotificationPermissionStep : InitialOnboardingStep()
    data object AuthGateStep : InitialOnboardingStep()
}

/**
 * Define los pasos posibles dentro del flujo de Personalización del Plan de Comidas (Fase 2).
 * Separarlo del Onboarding Inicial mantiene el código más limpio y organizado.
 */
sealed class MealPlanPersonalizationStep {
    data object MealCountStep : MealPlanPersonalizationStep()
    data object SuggestionPreferenceStep : MealPlanPersonalizationStep()
    data object FoodSelectionStep : MealPlanPersonalizationStep()
    data object DietVarietyStep : MealPlanPersonalizationStep()
}