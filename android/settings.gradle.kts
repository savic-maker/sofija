pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        // Android Gradle Plugin
        id("com.android.application") version "8.6.1"
        id("com.android.library") version "8.6.1"

        // Kotlin
        id("org.jetbrains.kotlin.android") version "2.1.10"
    }
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

// Flutter plugin loader (required by Flutter Android build)
val flutterSdkPath = run {
    val props = java.util.Properties()
    val localPropsFile = file("local.properties")
    require(localPropsFile.exists()) {
        """
        local.properties not found in android/.
        In CI you must create it with flutter.sdk=<path-to-flutter-sdk>.
        """.trimIndent()
    }
    localPropsFile.inputStream().use { props.load(it) }
    requireNotNull(props.getProperty("flutter.sdk")) { "flutter.sdk not set in local.properties" }
}

apply(from = "$flutterSdkPath/packages/flutter_tools/gradle/app_plugin_loader.gradle")
