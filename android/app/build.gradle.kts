plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "com.sofija.tracker"

    // Use Flutter-provided SDK values to avoid mismatches after Flutter upgrades
    compileSdk = flutter.compileSdkVersion
    ndkVersion = flutter.ndkVersion

    defaultConfig {
        applicationId = "com.sofija.tracker"

        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion

        versionCode = flutter.versionCode
        versionName = flutter.versionName

        multiDexEnabled = true
    }

    signingConfigs {
        // This lets CI build a "release" APK without setting up a keystore yet.
        // For Play Store, replace this with your own keystore config.
        getByName("debug")
    }

    buildTypes {
        release {
            // To ensure assembleRelease works in CI even without a release keystore:
            signingConfig = signingConfigs.getByName("debug")

            isMinifyEnabled = false
            isShrinkResources = false

            proguardFiles(
                getDefaultProguardFile("proguard-
