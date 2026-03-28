plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    `maven-publish`
}

group = "com.yusubov.compsebook"
version = "0.1.0"

kotlin {
    // ── Target: Desktop (JVM) ──
    // Produces a .jar file that runs on any OS with a JVM
    jvm("desktop")

    // ── Target: Web (WebAssembly) ──
    // Produces a .wasm + .js bundle that runs in modern browsers
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    // ── Source Sets (where your code lives) ──
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
    }
}
