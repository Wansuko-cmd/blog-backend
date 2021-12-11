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

        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-RC")

        projectImplementation(":utils")

        //test
        testImplementation(kotlin("test"))
    }

    tasks.test {
        useJUnitPlatform()
    }
}

fun Project.projectImplementation(dependency: String) {

    val projectDependency = project(dependency)

    if(project != projectDependency.project) {
        dependencies { implementation(projectDependency) }
    }
}
