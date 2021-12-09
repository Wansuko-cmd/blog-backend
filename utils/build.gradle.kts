plugins {
    kotlin("jvm")
}

dependencies {
    //datetime
    api("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")

    //log
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.0")
    implementation("org.slf4j:slf4j-simple:1.7.32")
}
