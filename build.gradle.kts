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
        mavenLocal()
        maven("https://papermc.io/repo/repository/maven-public/")
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
        compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
        compileOnly("me.lucko:helper:5.6.10")
    }
}
