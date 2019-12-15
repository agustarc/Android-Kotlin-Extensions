buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Plugins.gradle)
        classpath(kotlin("gradle-plugin", version = Plugins.kotlin))
    }
}

allprojects {
    repositories {
        maven(Repositories.MAVEN_GOOGLE)
        maven(Repositories.JITPACK)
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}