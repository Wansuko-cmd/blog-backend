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

        //log
        implementation(project(":utils:log"))

        implementation(project(":domains"))
        implementation(project(":databases:common"))
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")

        //test
        //H2
        testImplementation("com.h2database:h2:1.4.200")
        testImplementation(kotlin("test"))
    }

    tasks.test {
        useJUnitPlatform()
    }
}
