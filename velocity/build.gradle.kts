plugins {
    kotlin("jvm")
    kotlin("kapt")
    java
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "dev.lillian.bunishments"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://nexus.velocitypowered.com/repository/maven-public/")
}

dependencies {
    implementation(project(":api"))

    implementation(kotlin("stdlib"))
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("org.mongodb:mongodb-driver-sync:4.4.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation("org.mongojack:mongojack:4.3.0")

    compileOnly("com.velocitypowered:velocity-api:3.0.1")
    kapt("com.velocitypowered:velocity-api:3.0.1")
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveBaseName.set("bunishments")
        archiveClassifier.set("")
    }
}