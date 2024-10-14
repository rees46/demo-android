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
        // Загрузка версии из файла version.properties
        val versionPropsFile = File(project.rootProject.projectDir, "version.properties")
        val versionProps = Properties()
        if (versionPropsFile.exists()) {
            versionProps.load(FileInputStream(versionPropsFile))
        }

        val currentVersionCode = versionProps["VERSION_CODE"].toString().toInt()
        val currentVersionName = versionProps["VERSION_NAME"].toString()

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

            project.tasks.register("incrementVersion") {
                doLast {
                    val newVersionCode = currentVersionCode + 1
                    versionProps["VERSION_CODE"] = newVersionCode.toString()
                    versionProps.store(FileOutputStream(versionPropsFile), null)
                    println("Version updated to: $newVersionCode")
                }
            }
        }
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
        val localPropertiesFile = project.rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localProperties.load(FileInputStream(localPropertiesFile))
        }

        signingConfigs {
            create(RELEASE_CONFIG) {
                storeFile = File(localProperties.getProperty("RELEASE_STORE_FILE"))
                storePassword = localProperties.getProperty("RELEASE_STORE_PASSWORD")
                keyAlias = localProperties.getProperty("RELEASE_KEY_ALIAS")
                keyPassword = localProperties.getProperty("RELEASE_KEY_PASSWORD")
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
        private const val VERSION_CODE = 2
        private const val VERSION_NAME = "1.0.1"

        private const val APPLICATION_ID = "rees46.demo_android"
        private const val ANDROID_APPLICATION_LIB = "com.android.application"
        private const val ANDROID_LIB = "com.android.library"
        private const val MIN_SDK = 24
        private const val TARGET_SDK = 34
        private const val JVM_TARGET = "20"

        private const val RELEASE_TYPE = "release"
        private const val RELEASE_CONFIG = "releaseConfig"
        private const val DEBUG_TYPE = "debug"
        private const val DEBUG_CONFIG = "debugConfig"

        private const val DEV_FLAVOR = "dev"
        private const val STAGE_FLAVOR = "stage"
        private const val PROD_FLAVOR = "prod"
        private const val DIMENSION_FLAVORS = "medical"
        private const val APPLICATION_STAGE_SUFFIX = ".test"

        private const val PROGUARD_ANDROID_TXT = "proguard-android-optimize.txt"
        private const val PROGUARD_RULES = "proguard-rules.pro"
    }
}
