plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()

    gradlePluginPortal()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0")
    api("com.android.tools.build:gradle:8.5.2")
}

gradlePlugin {
    plugins {
        register("conventionPlugin") {
            id = "com.example.build.config"
            implementationClass = "AppBuildConfig"
        }
    }
}