val exposedVersion: String by project

plugins {
    kotlin("jvm")
}

subprojects {
    apply(plugin = "kotlin")

    dependencies {

        //log
        implementation(project(":utils:log"))

        //exposed
        implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
        implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
        implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
        implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")

        implementation(project(":domains"))

        projectImplementation(":databases:common")
    }
}

fun Project.projectImplementation(dependency: String) {

    val projectDependency = project(dependency)

    if(project != projectDependency.project) {
        dependencies { implementation(projectDependency) }
    }
}
