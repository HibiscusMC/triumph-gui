import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
}

dependencies {
    implementation(project(":triumph-gui"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            javaParameters = true
        }
    }
}