# CaloriFit - Aplicación de Nutrición y Fitness

## Descripción
CaloriFit es una aplicación móvil de nutrición y fitness que permite al usuario calcular, registrar y monitorear su ingesta calórica, su progreso físico y hábitos alimenticios, con un onboarding personalizado y visual moderno. [cite: 40] Se inspira en apps líderes como Fitia y Eatwise, pero con identidad propia. [cite: 41]

## Estado Actual del Proyecto (30 de Mayo, 2025)

### Funcionalidades y Pantallas Implementadas:

* **Configuración Inicial del Proyecto:**
    * Proyecto Android configurado con Kotlin, Jetpack Compose y Material 3.
    * Dependencias gestionadas mediante Version Catalogs (`libs.versions.toml`).
    * Plugins de Gradle (incluyendo `kotlin-kapt` y `hilt`) configurados.
* **Estructura de Carpetas:**
    * Organización de paquetes alineada con la arquitectura MVVM (`screens`, `components`, `ui.theme`, `navigation`, etc.).
* **Tema de la Aplicación (Branding Básico):**
    * `Color.kt`: Paleta de colores personalizada de CaloriFit definida (Primario: Azul, Secundario: Verde agua claro, Botón Acción: Verde `#3AC444`, etc.).
    * `Type.kt`: Escala tipográfica básica de Material 3 configurada.
    * `Shape.kt`: Formas de componentes (redondeo de esquinas) definidas.
    * `Theme.kt`: `CaloriFitTheme` aplicando los colores, tipografía y formas.
* **Diseño Edge-to-Edge:**
    * Configurado en `MainActivity` para que la UI se dibuje detrás de las barras de sistema.
* **SplashScreen (`SplashScreen.kt` en `screens.splash`):**
    * Fondo con degradado vertical personalizado (`CalorifitLightBackground` a `CalorifitGreenSecondary`).
    * Logo de la aplicación (`ic_logo_calorifit.xml`) centrado.
    * Animación de aparición (fade-in).
    * Barra de estado transparente con iconos oscuros.
    * Navegación automática temporizada a `WelcomeScreen`.
* **WelcomeScreen (`WelcomeScreen.kt` en `screens.welcome`):**
    * Fondo con degradado vertical personalizado.
    * Textos de bienvenida ("Un cuerpo fit empieza...", "Descubre tu mejor versión...").
    * Logo de la aplicación (`ic_logo_calorifit.xml`) mostrado.
    * Imagen ilustrativa (`img_healthy_bowl.png`).
    * Botón principal "Comencemos 👉" con estilo personalizado (color `#3AC444`, forma menos redondeada).
    * Texto "¿Ya tienes una cuenta? **Inicia sesión**" con la parte "Inicia sesión" clickeable.
    * `ModalBottomSheet` para opciones de inicio de sesión (se muestra al hacer clic en "Inicia sesión"):
        * Título "Elige un método para iniciar sesión".
        * Handle visual en la parte superior del sheet.
        * Fondo del sheet configurado a `MaterialTheme.colorScheme.surface`.
        * Botón "Continuar con Google" (con borde para visibilidad, acción placeholder).
        * Botón "Continuar con Correo" (con icono de email, navega a `LoginScreen`).
* **LoginScreen (`LoginScreen.kt` en `screens.login`):**
    * Creado como un placeholder básico.
* **Navegación Básica (Jetpack Navigation Compose en `MainActivity.kt`):**
    * `NavHostController` y `NavHost` configurados.
    * Rutas definidas: `"splash"`, `"welcome"`, `"login"`.
    * Flujo: `SplashScreen` -> `WelcomeScreen`. Desde `WelcomeScreen`, el link "Inicia sesión" abre el `ModalBottomSheet`, y el botón "Continuar con Correo" navega a `LoginScreen`.

### Tecnologías Clave Utilizadas (Hasta Ahora):
* Kotlin
* Jetpack Compose
* Material 3 (Componentes, Tematización)
* Jetpack Navigation Compose
* Gradle con Version Catalogs (`libs.versions.toml`)
* Hilt (Configuración inicial en Gradle, aún no usado activamente en el código de la app)

### Decisiones de Diseño y Configuración Clave:
* Prioridad a los mockups de Figma del usuario para la UI.
* Uso de `WindowCompat.setDecorFitsSystemWindows(window, false)` para UI Edge-to-Edge.
* Manejo de la visibilidad de iconos de la barra de estado (`isAppearanceLightStatusBars`).
* Solución de problemas de Gradle relacionados con `kotlin-kapt` y Hilt mediante la configuración de plugins y Version Catalogs.
* Uso de `LinkAnnotation` para texto parcialmente clickeable.
* Implementación de `ModalBottomSheet` para opciones de login.
* Imágenes/Logos: `ic_logo_calorifit.xml` (logo app), `img_healthy_bowl.png` (welcome), `ic_logo_google_g.xml` (logo Google).

## Próximos Pasos Inmediatos:

1.  **WelcomeScreen:**
    * Implementar la acción del botón "Comencemos 👉" para navegar al flujo de Onboarding.
    * Implementar la lógica real del botón "Continuar con Google" (integración con Firebase Auth).
2.  **LoginScreen (`LoginScreen.kt`):**
    * Diseñar e implementar la UI completa según las especificaciones (card elevada, campos de email/contraseña, botón de login, links secundarios).
    * Implementar la lógica de inicio de sesión con Firebase Auth (email/contraseña).
3.  **OnboardingScreen:**
    * Diseñar e implementar la primera pantalla del flujo de onboarding multipaso.
    * Configurar la navegación hacia y a través de los pasos del onboarding.
4.  **Revisar y Refinar:**
    * Ajustar estilos de texto (tipografía) y formas si es necesario.
    * Verificar la consistencia del branding.

---