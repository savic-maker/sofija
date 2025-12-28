plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "com.sofija.tracker"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.sofija.tracker"
        minSdk = 21
        targetSdk = 36

        versionCode = 1
        versionName = "1.0.0"

        // Needed sometimes when you have many deps
        multiDexEnabled = true
    }

    buildTypes {
        release {
            // Keep it simple & stable in CI
            isMinifyEnabled = false
            isShrinkResources = false

            // Default proguard config (won't run since minify=false, but harmless)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    compileOptions {
        // Java 17 (matches GitHub Actions Java setup)
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

        // Required by flutter_local_notifications and some modern libs
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Core desugaring required when enabled above
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    // Basic AndroidX (safe defaults)
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
}
