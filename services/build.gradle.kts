plugins {
    kotlin("jvm")
}

subprojects {
    apply(plugin = "kotlin")

    dependencies {

        implementation(project(":domains"))
        projectImplementation(
            dependency = ":services:utils",
            ignore =  listOf(":services:repositories", ":services:controllers", ":services:external")
        )
        projectImplementation(
            dependency = ":services:repositories",
            ignore =  listOf(":services:controllers", ":services:external")
        )
        projectImplementation(
            dependency = ":services:controllers",
            ignore =  listOf(":services:repositories", ":services:external")
        )
        projectImplementation(dependency = ":services:external")
    }
}

fun Project.projectImplementation(dependency: String, ignore: List<String> = listOf()) {

    val projectDependency = project(dependency)
    val ignoreProject = ignore.map { project(it) }

    if(project != projectDependency.project && project !in ignoreProject) {
        dependencies { implementation(projectDependency) }
    }
}
