plugins {
    application
    kotlin("jvm") version "1.9.23"
}

application {
    mainClass = "fpinkotlin.MainKt"
}

group = "fpinkotlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
