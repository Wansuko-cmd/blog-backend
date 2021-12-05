plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":services:utils"))
    implementation(project(":services:article"))
    implementation(project(":repositories:utils"))
}
