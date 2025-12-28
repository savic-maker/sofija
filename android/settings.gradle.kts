import java.util.Properties

// Read Flutter SDK path from android/local.properties
val localProperties = Properties()
val localPropertiesFile = file("local.properties")
require(localPropertiesFile.exists()) {
    """
    local.properties not found in android/.
    In CI you must create it with flutter.sdk=<path-to-flutter-sdk>.
    """.trimIndent()
}
localPropertiesFile.inputStream().use { localProperties.load(it) }

val flutterSdkPath = localProperties.getProperty("flutter.sdk")
    ?: error("flutter.sdk not set in local.properties")

pluginManagement {
    // This replaces the old imperative apply(from=.../app_plugin_loader.gradle)
    includeBuild("$flutterSdkPath/packages/flutter_tools/gradle")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    // Flutter plugin loader (required by Flutter Android build)
    id("dev.flutter.flutter-plugin-loader") version "1.0.0"

    // Keep your existing versions for now (we can align them later if needed)
    id("com.android.application") version "8.6.1" apply false
    id("com.android.library") version "8.6.1" apply false
    id("org.jetbrains.kotlin.android") version "2.1.10" apply false
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "sofija_tracker"
include(":app")
