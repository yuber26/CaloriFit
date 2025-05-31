plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose) // Si lo aplicas aquí

    alias(libs.plugins.kotlin.kapt)             // Para habilitar kapt
    alias(libs.plugins.google.dagger.hilt.android) // Para aplicar Hilt
}

android {
    namespace = "com.calorifit.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.calorifit.app"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)

    // Core Android KTX (extensiones Kotlin)
    implementation(libs.androidx.core.ktx) // [cite: 30]

    // Lifecycle (para observar estados, etc.)
    implementation(libs.androidx.lifecycle.runtime.ktx) // [cite: 30]

    // Activity Compose (integración de Compose con Activities)
    implementation(libs.androidx.activity.compose) // [cite: 30]

    // Compose Bill of Materials (BOM) - Ayuda a manejar versiones consistentes de librerías Compose
    implementation(platform(libs.androidx.compose.bom.v20250501)) // [cite: 30]
    // Las siguientes librerías de Compose no necesitan versión porque la toman del BOM:
    implementation(libs.ui) // [cite: 30]
    implementation(libs.ui.graphics) // [cite: 30]
    implementation(libs.ui.tooling.preview) // [cite: 30] Para las @Preview
    implementation(libs.material3) // [cite: 30] Para componentes Material 3

    // Splash Screen API (para la pantalla de inicio moderna)
    implementation(libs.androidx.core.splashscreen) // [cite: 30]

    // Navegación con Compose (la necesitaremos muy pronto)
    implementation(libs.androidx.navigation.compose) // [cite: 30]

    // Hilt (Inyección de dependencias)
    implementation(libs.google.dagger.hilt.android) // [cite: 30]
    kapt(libs.hilt.compiler) // [cite: 30]
    implementation(libs.androidx.hilt.navigation.compose) // [cite: 30] Para integrar Hilt con Navegación Compose

    // Firebase (las puedes añadir ahora o cuando empecemos a usarlas)
    // implementation(platform("com.google.firebase:firebase-bom:33.14.0")) // [cite: 30]
    // implementation("com.google.firebase:firebase-auth-ktx") // [cite: 30]
    // implementation("com.google.firebase:firebase-firestore-ktx") // [cite: 30]
    // implementation("com.google.firebase:firebase-analytics-ktx") // [cite: 30]

    // Otras que listaste y podrías necesitar más adelante:
    // ML Kit, Stripe, OneSignal, OpenAI, Accompanist Pager
    // implementation("com.google.mlkit:image-labeling:17.0.9") // [cite: 30]
    // implementation("com.stripe:stripe-android:21.14.0") // [cite: 30]
    // implementation("com.onesignal:OneSignal:5.1.33") // [cite: 30]
    // implementation("com.aallam.openai:openai-client:4.0.1") // [cite: 30]
    // implementation("com.google.accompanist:accompanist-pager:0.36.0") // [cite: 30]
    // implementation("com.google.accompanist:accompanist-pager-indicators:0.36.0") // [cite: 30]


    // Dependencias para Testing (ejemplos)
    // testImplementation("junit:junit:4.13.2")
    // androidTestImplementation("androidx.test.ext:junit:1.2.1")
    // androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    // androidTestImplementation(platform("androidx.compose:compose-bom:2025.05.01")) // Usa el mismo BOM
    // androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    // debugImplementation("androidx.compose.ui:ui-tooling") // Para el Layout Inspector, etc.
    // debugImplementation("androidx.compose.ui:ui-test-manifest")
}