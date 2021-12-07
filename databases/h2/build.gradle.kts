plugins {
    kotlin("jvm")
}

dependencies {

    //H2
    implementation("com.h2database:h2:1.4.200")

    implementation(project(":databases:dev"))
    implementation(project(":services:utils"))

    testImplementation(project(":domains"))
}
