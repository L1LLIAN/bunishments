plugins {
    kotlin("jvm")
    java
}

group = "dev.lillian.bunishments"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains:annotations:23.0.0")
}