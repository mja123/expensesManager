import org.gradle.internal.impldep.org.eclipse.jgit.lib.ObjectChecker.type

plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("mysql:mysql-connector-java:8.0.33")

}


tasks.test {
    useJUnitPlatform()
}

task("execute", JavaExec::class) {
    group = "Execution"
    description = "Run the main class with JavaExecTask"
    classpath = sourceSets["main"].runtimeClasspath

}