plugins {
    kotlin("jvm")
}

subprojects {
    apply(plugin = "kotlin")

    dependencies {

        implementation(project(":domains"))
        projectImplementation(":services:utils")
    }
}

fun Project.projectImplementation(dependency: String) {

    val projectDependency = project(dependency)

    if(project != projectDependency.project) {
        dependencies { implementation(projectDependency) }
    }
}
