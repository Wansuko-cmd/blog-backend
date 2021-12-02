plugins {
    kotlin("jvm")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
    implementation(project(":domains"))
    implementation(project(":services:utils"))
}
