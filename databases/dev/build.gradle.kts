plugins {
    kotlin("jvm")
}

dependencies {

    implementation(project(":domains"))

    //H2
    implementation("com.h2database:h2:1.4.200")
}
