pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ComposeBook"

// These three lines make folders into modules:
include(":composebook")       // maps to ./composebook/
include(":sample")