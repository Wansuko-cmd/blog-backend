val exposedVersion: String by project
val flywayVersion: String by project
val hikariVersion: String by project

plugins {
    kotlin("jvm")
}

dependencies {
    //exposed
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")

    //flyway
    implementation("org.flywaydb:flyway-core:$flywayVersion")

    //hikari
    implementation("com.zaxxer:HikariCP:$hikariVersion")

    //H2(バージョンを変えると上手く動かない)
    implementation("com.h2database:h2:1.4.200")

    implementation(project(":domain"))
}
