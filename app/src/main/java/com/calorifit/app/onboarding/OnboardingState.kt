package com.calorifit.app.onboarding

/**
 * Representa el estado completo de los datos recopilados durante todo el flujo de onboarding.
 * Actúa como la única fuente de verdad para la información del usuario antes de crear el perfil final.
 * Las propiedades son nulas/vacías por defecto, y se van rellenando a medida que el usuario avanza.
 */
data class OnboardingState(
    // --- Fase 1: Onboarding Inicial ---

    /** El objetivo principal del usuario (ej: "Perder Grasa"). */
    val goal: String? = null,

    /** La intención clave del usuario, define si se activará la Fase 2. */
    val wantsMealPlan: Boolean = false,

    /** El género seleccionado por el usuario. */
    val gender: String? = null,

    /** La edad del usuario. */
    val age: Int? = null,

    /** La altura del usuario, almacenada en centímetros para consistencia. */
    val heightInCm: Int? = null,

    /** El peso del usuario, almacenado en kilogramos para consistencia. */
    val weightInKg: Float? = null,

    /** El nivel de actividad física declarado (ej: "Sedentario", "Ligeramente Activo"). */
    val activityLevel: String? = null,

    /** Si el usuario realiza o no entrenamientos de fuerza. */
    val doesStrengthTraining: Boolean? = null,

    /** El peso objetivo que el usuario desea alcanzar. */
    val targetWeightInKg: Float? = null,

    /** La velocidad de progreso elegida (ej: "Recomendado", "Rápido"). */
    val pace: String? = null,


    // --- Fase 2: Personalización del Plan de Comidas ---

    /** Las comidas principales y snacks que el usuario desea tener (ej: ["Desayuno", "Almuerzo", "Cena"]). */
    val mealsPerDay: List<String> = emptyList(),

    /** La preferencia de cómo se le presentarán las comidas (ej: "Recetas", "Solo ingredientes"). */
    val suggestionPreference: String? = null,

    /** Lista de alimentos de la categoría Proteínas que el usuario prefiere. */
    val preferredProteins: List<String> = emptyList(),

    /** Lista de alimentos de la categoría Carbohidratos que el usuario prefiere. */
    val preferredCarbs: List<String> = emptyList(),

    /** Lista de alimentos de la categoría Grasas que el usuario prefiere. */
    val preferredFats: List<String> = emptyList(),

    /** Lista de alimentos de la categoría Lácteos y Bebidas que el usuario prefiere. */
    val preferredDairyAndDrinks: List<String> = emptyList(),

    /** Lista de alimentos de la categoría Frutas que el usuario prefiere. */
    val preferredFruits: List<String> = emptyList(),

    /** Lista de alimentos de la categoría Verduras que el usuario prefiere. */
    val preferredVegetables: List<String> = emptyList(),

    /** El nivel de variedad deseado en el plan de comidas. */
    val dietVariety: String? = null
)