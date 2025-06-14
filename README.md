# CaloriFit - Aplicación de Nutrición y Fitness (Estado: 9 Junio 2025)

## 1. Descripción General
CaloriFit es una aplicación móvil nativa de Android para nutrición y fitness. Permite a los usuarios calcular, registrar y monitorear su ingesta calórica, progreso físico y hábitos alimenticios. La app cuenta con un onboarding personalizado y un diseño visual moderno basado en Material 3.

## 2. Progreso y Funcionalidades Implementadas

### 2.1. Configuración del Proyecto y Arquitectura Base
* **Lenguaje y UI:** Kotlin 100%, Jetpack Compose.
* **Diseño:** Material 3 (Material You) implementado como base.
* **Build System:** Gradle con Kotlin DSL (`.kts`) y **Version Catalogs (`libs.versions.toml`)**.
* **Estructura de Paquetes:** Organizada para MVVM y escalabilidad, con paquetes por feature en `screens` (ej. `login`, `register`, `forgotpassword`).
* **Dependencias Clave Configuradas:** AndroidX Core, Activity Compose, Lifecycle, Compose UI, Compose Material 3, Navigation Compose.
* **Diseño Edge-to-Edge:** Habilitado globalmente.

### 2.2. Tematización (`ui.theme`)
* **`Color.kt`:** Paleta de colores de branding definida (`CalorifitBluePrimary`, `CalorifitGreenButtonAction`, etc.).
* **`Theme.kt`:** `CaloriFitTheme` configurado con `lightColorScheme`.
* **`Type.kt` y `Shape.kt`:** Tipografía y formas base de Material 3.

### 2.3. Pantallas y Navegación (Flujo de Autenticación Completo a Nivel de UI)
* **`MainActivity.kt`:**
    * Configura `NavHostController` y `NavHost`.
    * [cite_start]Rutas actuales: `"splash"`, `"welcome"`, `"login"`, `"register"`, `"forgot_password"`. 
* **`SplashScreen.kt`:**
    * [cite_start]Fondo degradado, logo con animación de fade-in y transición automática. 
* **`WelcomeScreen.kt`:**
    * UI principal y `ModalBottomSheet` para opciones de login.
* **`LoginScreen.kt` (`screens.login`):**
    * UI completa con `Card` elevada, campos de email/contraseña y toggle de visibilidad.
    * Navegación a las pantallas de registro y olvido de contraseña.
* **`ForgotPasswordScreen.kt` (`screens.forgotpassword`):**
    * UI completa con `TopAppBar` transparente y botón de retroceso `AutoMirrored`.
    * **Confirmación en pantalla:** Usa un `AlertDialog` para notificar al usuario que el enlace fue enviado, en lugar de una pantalla nueva.
    * **Lógica de Reenvío:** Incluye un contador de 60 segundos y la opción de "Reenviar Correo", que aparece después del primer intento.
* **`RegisterScreen.kt` (`screens.register`):**
    * UI completa basada en el mockup `LoginFrame-3.jpg`.
    * Campos para Nombre, Email, Contraseña y Confirmar Contraseña.
    * **Validación de Contraseña en Tiempo Real:**
        * Muestra los requisitos de la contraseña (longitud, mayúscula, número) solo cuando el campo tiene el foco, usando `onFocusChanged`.
        * Los requisitos cambian de icono y color (de ❌ a ✔️) a medida que se cumplen.
        * El campo "Confirmar contraseña" muestra un mensaje de error y se marca en rojo si no coincide con la contraseña.
    * `Checkbox` con enlaces clickeables para "Términos y Condiciones" y "Política de Privacidad".
    * El botón "Crear cuenta" se habilita dinámicamente solo cuando todas las validaciones son correctas (`derivedStateOf`).

### 2.4. Decisiones de Diseño y Buenas Prácticas Aplicadas
* **Consistencia de UI:** Se mantiene un estilo visual coherente en todas las pantallas de autenticación.
* **Manejo de Estado de UI:** Uso de `rememberSaveable` para persistir el estado de los formularios, `derivedStateOf` para validaciones complejas y `mutableIntStateOf` para optimización de primitivos.
* **UX Moderna:** Implementación de `LinkAnnotation` para textos parcialmente clickeables, `imePadding()` para manejar el teclado, y feedback visual en tiempo real para el usuario.
* **Componentes Reutilizables:** Creación de un Composable `PasswordRequirement` para los textos de ayuda de la contraseña.
* **Internacionalización:** Uso de iconos `AutoMirrored` (ej. `ArrowBack`) para un correcto funcionamiento en diseños RTL.

## 3. Próximos Pasos Inmediatos
1.  **OnboardingScreen:**
    * [cite_start]Diseñar e implementar el flujo multipaso para la recolección de datos del usuario (objetivos, género, edad, etc.). 
    * Conectar la navegación desde el botón "Comencemos 👉" en `WelcomeScreen`.
2.  **Lógica de Backend con Firebase:**
    * [cite_start]Integrar Firebase Authentication para implementar el registro, inicio de sesión (email/password, Google), y la funcionalidad de restablecer contraseña.