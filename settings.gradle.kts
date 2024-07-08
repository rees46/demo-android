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
include(":entities")
include(":feature")
include(":feature:main")
include(":feature:main:cart")
include(":feature:recommendationBlock")
include(":feature:main:home")
include(":feature:main:category")
include(":feature:main:settings")
include(":feature:cardProduct")
