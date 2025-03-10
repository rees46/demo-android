// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
	id("java")
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
}

buildscript {
    repositories { google() }
    dependencies {
        classpath(libs.google.services)
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
