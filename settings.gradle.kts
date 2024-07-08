pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "demo-android"
include(":app")
include(":data")
include(":domain:feature")
include(":domain:feature:main")
include(":domain:feature:main:cart")
include(":domain:feature:main:home")
include(":domain:feature:main:category")
include(":domain:feature:main:settings")
include(":domain:feature:recommendationBlock")
include(":domain:feature:cardProduct")
