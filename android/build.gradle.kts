allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// Keep Android build outputs in the Flutter build/ folder (standard Flutter layout)
rootProject.buildDir = file("../build")

subprojects {
    // Put each module output under root build dir
    project.buildDir = file("${rootProject.buildDir}/${project.name}")

    // Ensures :app is evaluated first (avoids some ordering issues in multi-module builds)
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
