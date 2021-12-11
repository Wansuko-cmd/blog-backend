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

        implementation(project(":databases:common"))

        implementation(project(":services:utils"))

        projectImplementation(":repositories:utils")


        //test
        //H2
        testImplementation("com.h2database:h2:1.4.200")

        implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.0")

        implementation("com.zaxxer:HikariCP:5.0.0")
    }
}

fun Project.projectImplementation(dependency: String) {

    val projectDependency = project(dependency)

    if(project != projectDependency.project) {
        dependencies { implementation(projectDependency) }
    }
}
