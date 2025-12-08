import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.AppImage
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Exe

plugins {
    alias(libs.plugins.build.config)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hotReload)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.shadow)
}

version = "0.0.1"

buildConfig {
    buildConfigField("VERSION", provider { "${project.version}" })
}

kotlin {
    jvmToolchain(libs.versions.jdk.get().toInt())

    jvm()

//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        browser()
//        binaries.executable()
//    }

    sourceSets {
        all {
            languageSettings.enableLanguageFeature("NestedTypeAliases")
            languageSettings.enableLanguageFeature("ExplicitBackingFields")
        }

        commonMain.dependencies {
            implementation(libs.bundles.filekit)
            implementation(libs.bundles.ktor)

            implementation(libs.bandkit)

            implementation(compose.components.uiToolingPreview)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.ui)

            implementation(libs.androidx.lifecycle.runtime)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.navigation.compose)

            implementation(libs.coil)
            implementation(libs.coil.network.ktor)

            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.kstore)

            implementation(libs.materialKolor)

            implementation(libs.reorderable)

            implementation(libs.zip4j)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))

            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)

            implementation(libs.kotlinx.coroutines.test)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)

            implementation(libs.kotlinx.coroutines.swing)

            implementation(libs.kstore.file)
        }

//        webMain.dependencies {
//            implementation(libs.kstore.storage)
//        }
    }
}

compose.desktop {
    application {
        // Because I'm too lazy to fight ProGuard right now.
        buildTypes.release.proguard.isEnabled = false

        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(AppImage, Exe)
            packageName = "BEx"
            packageVersion = "${project.version}"

            linux {
                iconFile.set(project.file("desktopAppIcons/LinuxIcon.png"))
            }

            windows {
                iconFile.set(project.file("desktopAppIcons/WindowsIcon.ico"))
            }

            macOS {
                iconFile.set(project.file("desktopAppIcons/MacosIcon.icns"))
                bundleID = "uk.co.harnick.bex.desktopApp"
            }
        }
    }
}

tasks.register<ShadowJar>("fatJar") {
    archiveBaseName.set("BEx")

    manifest {
        attributes["Main-Class"] = "MainKt"
    }

    val main by kotlin.jvm().compilations

    from(main.output.allOutputs)

    configurations = listOf(
        project.configurations.getByName(main.runtimeDependencyConfigurationName)
    )
}
