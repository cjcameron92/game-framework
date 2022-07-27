plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "4.0.1" apply false
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

buildscript {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "com.github.johnrengelman.shadow")

    group = "com.games"

    repositories {
        mavenCentral()
    }

    dependencies {
        api("org.jetbrains:annotations:23.0.0")
    }
}
