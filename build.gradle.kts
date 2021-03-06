plugins {
    kotlin("jvm") version "1.6.0"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "kotlin")

    dependencies {

        projectImplementation(":utils")

        //test
        testImplementation(kotlin("test"))
    }
}

fun Project.projectImplementation(dependency: String) {

    val projectDependency = project(dependency)

    if (project != projectDependency.project) {
        dependencies { implementation(projectDependency) }
    }
}
