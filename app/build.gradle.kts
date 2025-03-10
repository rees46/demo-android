plugins {
    alias(libs.plugins.triplet.plugin)
    id("com.google.gms.google-services")
	id("com.android.application") version "8.5.2" apply false
	kotlin("android")
}

play {
    serviceAccountCredentials = file("play-account.json")
    track = "internal"
    defaultToAppBundles = true
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "rees46.demo_shop"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
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
    implementation(libs.rees46.sdk)
    implementation(project(":core"))
    implementation(project(":feature"))
    implementation(project(":navigation"))
}

    signingConfigs {
        create("release") {
            storeFile = file(System.getenv("KEYSTORE_PATH") ?: "keystore.jks")
            storePassword = System.getenv("SIGNING_STORE_PASSWORD") ?: "password"
            keyAlias = System.getenv("SIGNING_KEY_ALIAS") ?: "keyAlias"
            keyPassword = System.getenv("SIGNING_KEY_PASSWORD") ?: "password"
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
    implementation(libs.rees46.sdk)
    implementation(project(":core"))
    implementation(project(":feature"))
    implementation(project(":navigation"))
}
           isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
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
    implementation(libs.rees46.sdk)
    implementation(project(":core"))
    implementation(project(":feature"))
    implementation(project(":navigation"))
}
   implementation(project(":feature"))
    implementation(project(":navigation"))
}
