@file:Suppress("DEPRECATION")

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AppBuildConfig : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.withId(ANDROID_APPLICATION_LIB) {
            configureAppExtension(project.extensions.getByType(AppExtension::class.java))
        }

        project.plugins.withId(ANDROID_LIB) {
            configureLibraryExtension(project.extensions.getByType(LibraryExtension::class.java))
        }

        configureKotlinCompile(project)
    }

    private fun configureAppExtension(androidExtension: AppExtension) {
        androidExtension.apply {
            compileSdkVersion(TARGET_SDK)
            configureDefaultConfig()
            flavorDimensions(DIMENSION_FLAVORS)
            configureProductFlavors()
            configureSigningConfigs()
            configureBuildTypes()
            configurePackagingOptions()
            configureJavaAndKotlinOptions()
        }
    }

    private fun AppExtension.configureDefaultConfig() {
        defaultConfig {
            applicationId = APPLICATION_ID
            minSdk = MIN_SDK
            targetSdk = TARGET_SDK
            versionCode = VERSION_CODE
            versionName = VERSION_NAME
            viewBinding {
                enable = true
            }
        }
    }

    private fun AppExtension.configureProductFlavors() {
        productFlavors {
            create(DEV_FLAVOR) {
                dimension = DIMENSION_FLAVORS
                // applicationIdSuffix = APPLICATION_DEV_SUFFIX
            }
            create(STAGE_FLAVOR) {
                dimension = DIMENSION_FLAVORS
                applicationIdSuffix = APPLICATION_STAGE_SUFFIX
            }
            create(PROD_FLAVOR) {
                dimension = DIMENSION_FLAVORS
            }
        }
    }

    private fun AppExtension.configureSigningConfigs() {
        signingConfigs {
            create(RELEASE_CONFIG) {
                // Configuration for releaseConfig
            }
            create(DEBUG_CONFIG) {
                // Configuration for debugConfig
            }
        }
    }

    private fun AppExtension.configureBuildTypes() {
        buildTypes {
            getByName(RELEASE_TYPE) {
                signingConfig = signingConfigs.getByName(RELEASE_CONFIG)
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile(PROGUARD_ANDROID_TXT),
                    PROGUARD_RULES
                )
            }
            getByName(DEBUG_TYPE) {
                isDebuggable = true
            }
        }
    }

    private fun AppExtension.configurePackagingOptions() {
        packagingOptions {
            exclude("META-INF/*.kotlin_module")
            exclude("META-INF/AL2.0")
            exclude("META-INF/LGPL2.1")
        }
    }

    private fun configureLibraryExtension(androidExtension: LibraryExtension) {
        androidExtension.apply {
            compileSdk = TARGET_SDK
            configureDefaultConfig()
            configureBuildTypes()
            configureJavaAndKotlinOptions()
        }
    }

    private fun LibraryExtension.configureDefaultConfig() {
        defaultConfig {
            minSdk = MIN_SDK
            targetSdk = TARGET_SDK
        }
    }

    private fun LibraryExtension.configureBuildTypes() {
        buildTypes {
            getByName(RELEASE_TYPE) {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile(PROGUARD_ANDROID_TXT),
                    PROGUARD_RULES
                )
            }
        }
    }

    private fun configureKotlinCompile(project: Project) {
        project.tasks.withType(KotlinCompile::class.java).configureEach {
            kotlinOptions {
                jvmTarget = JVM_TARGET
            }
        }
    }

    private fun AppExtension.configureJavaAndKotlinOptions() {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_20
            targetCompatibility = JavaVersion.VERSION_20
        }
    }

    private fun LibraryExtension.configureJavaAndKotlinOptions() {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_20
            targetCompatibility = JavaVersion.VERSION_20
        }
    }

    companion object {
        private const val APPLICATION_ID = "rees46.demo_android"
        private const val ANDROID_APPLICATION_LIB = "com.android.application"
        private const val ANDROID_LIB = "com.android.library"
        private const val MIN_SDK = 24
        private const val TARGET_SDK = 34
        private const val VERSION_CODE = 1
        private const val VERSION_NAME = "1.0.0"
        private const val JVM_TARGET = "20"

        private const val RELEASE_TYPE = "release"
        private const val RELEASE_CONFIG = "releaseConfig"
        private const val DEBUG_TYPE = "debug"
        private const val DEBUG_CONFIG = "debugConfig"

        private const val DEV_FLAVOR = "dev"
        private const val STAGE_FLAVOR = "stage"
        private const val PROD_FLAVOR = "prod"
        private const val DIMENSION_FLAVORS = "medical"
        private const val APPLICATION_DEV_SUFFIX = ".debug"
        private const val APPLICATION_STAGE_SUFFIX = ".test"

        private const val PROGUARD_ANDROID_TXT = "proguard-android-optimize.txt"
        private const val PROGUARD_RULES = "proguard-rules.pro"
    }
}