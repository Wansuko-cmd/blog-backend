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

        //log
        projectImplementation(":utils:log")

        //datetime
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")

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
