import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(AndroidSdk.COMPILE)

    defaultConfig {
        minSdkVersion(AndroidSdk.MIN)
        targetSdkVersion(AndroidSdk.TARGET)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))

    kapt(JetPack.Lifecycle.compiler)

    api(JetPack.Lifecycle.java8)
    api(JetPack.Lifecycle.runtime)
    api(JetPack.Lifecycle.extensions)
}
