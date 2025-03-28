@file:Suppress("DEPRECATION")

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Properties
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AppBuildConfig : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.withId(ANDROID_APPLICATION_LIB) {
            configureAppExtension(project.extensions.getByType(AppExtension::class.java), project)
        }

        project.plugins.withId(ANDROID_LIB) {
            configureLibraryExtension(project.extensions.getByType(LibraryExtension::class.java))
        }

        configureKotlinCompile(project)
    }

    private fun configureAppExtension(androidExtension: AppExtension, project: Project) {
        val versionPropsFile = File(project.rootProject.projectDir, "version.properties")
        val versionProps = Properties()
        if (versionPropsFile.exists()) {
            versionProps.load(FileInputStream(versionPropsFile))
        }

        val currentVersionCode = versionProps[VERSION_CODE]?.toString()?.toInt() ?: 1
        val currentVersionName = versionProps[VERSION_NAME]?.toString() ?: "1.0.0"

        androidExtension.apply {
            compileSdkVersion(TARGET_SDK)
            defaultConfig {
                applicationId = APPLICATION_ID
                minSdk = MIN_SDK
                targetSdk = TARGET_SDK
                versionCode = currentVersionCode
                versionName = currentVersionName
                viewBinding {
                    enable = true
                }
            }
            flavorDimensions(DIMENSION_FLAVORS)
            configureProductFlavors()
            configureSigningConfigs(project)
            configureBuildTypes()
            configurePackagingOptions()
            configureJavaAndKotlinOptions()

            project.tasks.register(PUBLISH_TO_ITERNAL_TESTING) {
                dependsOn(RELEASE_BUNDLE)
                finalizedBy(PUBLISH_RELEASE_BUNDLE)
            }

            project.tasks.register(INCREMENT_VERSION) {
                doLast {
                    incrementVersion(versionProps, versionPropsFile)
                }
            }
        }
    }

    private fun incrementVersion(versionProps: Properties, versionPropsFile: File) {
        val currentVersionCode = versionProps[VERSION_CODE]?.toString()?.toInt() ?: 1
        val newVersionCode = currentVersionCode + 1

        val currentVersionName = versionProps[VERSION_NAME]?.toString() ?: "1.0.0"
        val versionNameParts = currentVersionName.split(".").toMutableList()

        if (versionNameParts.size == 3) {
            val patchVersion = versionNameParts[2].toIntOrNull() ?: 0
            versionNameParts[2] = (patchVersion + 1).toString()
        }

        val newVersionName = versionNameParts.joinToString(".")

        versionProps[VERSION_CODE] = newVersionCode.toString()
        versionProps[VERSION_NAME] = newVersionName
        versionProps.store(FileOutputStream(versionPropsFile), null)

        println("Version updated to: versionCode=$newVersionCode, versionName=$newVersionName")
    }

    private fun AppExtension.configureProductFlavors() {
        productFlavors {
            create(DEV_FLAVOR) {
                dimension = DIMENSION_FLAVORS
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

    private fun AppExtension.configureSigningConfigs(project: Project) {
        val localProperties = Properties()
        val localPropertiesFile = project.rootProject.file(LOCAL_PROPERTIES_FILE)
        if (localPropertiesFile.exists()) {
            localProperties.load(FileInputStream(localPropertiesFile))
        }

        signingConfigs {
            create(RELEASE_CONFIG) {
                storeFile = File(
                    localProperties.getProperty(RELEASE_STORE_FILE) ?: System.getenv("SIGNING_STORE_FILE")
                )
                storePassword = localProperties.getProperty(RELEASE_STORE_PASSWORD) ?: System.getenv("SIGNING_STORE_PASSWORD")
                keyAlias = localProperties.getProperty(RELEASE_KEY_ALIAS) ?: System.getenv("SIGNING_KEY_ALIAS")
                keyPassword = localProperties.getProperty(RELEASE_KEY_PASSWORD) ?: System.getenv("SIGNING_KEY_PASSWORD")
            }
            create(DEBUG_CONFIG) {
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
        private const val PUBLISH_TO_ITERNAL_TESTING = "publishProdReleaseToPlay"
        private const val PUBLISH_RELEASE_BUNDLE = "publishProdReleaseBundle"
        private const val LOCAL_PROPERTIES_FILE = "local.properties"
        private const val INCREMENT_VERSION = "incrementVersion"
        private const val RELEASE_BUNDLE = "bundleProdRelease"

        private const val VERSION_NAME = "VERSION_NAME"
        private const val VERSION_CODE = "VERSION_CODE"

        private const val RELEASE_STORE_PASSWORD = "RELEASE_STORE_PASSWORD"
        private const val RELEASE_KEY_PASSWORD = "RELEASE_KEY_PASSWORD"
        private const val RELEASE_STORE_FILE = "RELEASE_STORE_FILE"
        private const val RELEASE_KEY_ALIAS = "RELEASE_KEY_ALIAS"

        private const val ANDROID_APPLICATION_LIB = "com.android.application"
        private const val APPLICATION_ID = "personaclick.demo_android"
        private const val ANDROID_LIB = "com.android.library"
        private const val JVM_TARGET = "20"
        private const val TARGET_SDK = 34
        private const val MIN_SDK = 24

        private const val RELEASE_CONFIG = "releaseConfig"
        private const val DEBUG_CONFIG = "debugConfig"
        private const val RELEASE_TYPE = "release"
        private const val DEBUG_TYPE = "debug"

        private const val STAGE_FLAVOR = "stage"
        private const val PROD_FLAVOR = "prod"
        private const val DEV_FLAVOR = "dev"
        private const val APPLICATION_STAGE_SUFFIX = ".test"
        private const val DIMENSION_FLAVORS = "demoShop"

        private const val PROGUARD_ANDROID_TXT = "proguard-android-optimize.txt"
        private const val PROGUARD_RULES = "proguard-rules.pro"
    }
}