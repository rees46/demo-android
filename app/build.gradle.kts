plugins {
    id("com.example.build.config")
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.triplet.plugin)
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
}

play {
    serviceAccountCredentials = file("play-account.json")
    track = "internal"
    defaultToAppBundles = true
}

android {
    namespace = "personaclick.demo_android.app"

    defaultConfig {
        applicationId =  "personaclick.demo_shop"
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
    implementation(libs.personaclick.sdk)
    implementation(project(":core"))
    implementation(project(":feature"))
    implementation(project(":navigation"))
}