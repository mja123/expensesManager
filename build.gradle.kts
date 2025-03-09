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
    implementation("mysql:mysql-connector-java:8.0.33")
}


task("execute", JavaExec::class) {
    group = "Execution"
    description = "Run the main class with JavaExecTask"
    classpath = sourceSets["main"].runtimeClasspath

}