import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.21"
    id("com.github.johnrengelman.shadow") version "6.1.0"

    // Apply the application plugin to add support for building a CLI application.
    application
    java
}

repositories {
    mavenCentral()
}

application {
    // Desired way mainClass.set("<main class>") causes an issue during jar packaging
    @Suppress("DEPRECATION")
    mainClassName ="cz.speedygonzales.TotalBattleApp"
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.github.kwhat:jnativehook:2.2.2")

    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("ch.qos.logback:logback-classic:1.4.11")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}


tasks.withType<ShadowJar>() {
    manifest {
        attributes["Main-Class"] = "cz.speedygonzales.TotalBattleApp"
    }
}