val exposedVersion: String by project

plugins {
    kotlin("jvm")
}

subprojects {
    apply(plugin = "kotlin")

    dependencies {
        //exposed
        implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
        implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
        implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
        implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")

        implementation(project(":domains"))
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
    }
}
