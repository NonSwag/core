plugins {
    id("java")
    id("java-library")
    id("maven-publish")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
    withSourcesJar()
    withJavadocJar()
}

tasks.compileJava {
    options.release.set(21)
}

group = "net.thenextlvl.core"
version = "1.0.20"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    api(project(":utils"))
    api(project(":files"))

    compileOnlyApi("net.kyori:adventure-text-minimessage:4.18.0")

    compileOnly("org.jspecify:jspecify:1.0.0")
    compileOnly("org.projectlombok:lombok:1.18.36")

    testImplementation("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    testImplementation(project(":utils"))
    testImplementation(project(":files"))

    annotationProcessor("org.projectlombok:lombok:1.18.36")

    testImplementation("org.junit.jupiter:junit-jupiter:5.12.0-SNAPSHOT")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showCauses = true
        showExceptions = true
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
    repositories.maven {
        val channel = if ((version as String).contains("-pre")) "snapshots" else "releases"
        url = uri("https://repo.thenextlvl.net/$channel")
        credentials {
            username = System.getenv("REPOSITORY_USER")
            password = System.getenv("REPOSITORY_TOKEN")
        }
    }
}