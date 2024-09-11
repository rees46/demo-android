plugins {
    id("com.example.convention")
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "rees46.demo_android.app"

    defaultConfig {
        applicationId =  "rees46.demo_android"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compat)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.dagger)
    implementation(libs.rees46.sdk)
    annotationProcessor(libs.dagger.compiler)
    implementation(project(":core"))
    implementation(project(":feature"))
    implementation(project(":navigation"))
}
