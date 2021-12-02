plugins {
    kotlin("jvm")
}

subprojects {
    apply(plugin = "kotlin")

    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
        implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
        implementation(project(":domains"))
    }
}
