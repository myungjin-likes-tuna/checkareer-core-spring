import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

repositories {
    mavenCentral()
}

subprojects {
    apply {
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    group = "com.mlt"
    version = "0.0.1-SNAPSHOT"

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        "implementation"("org.springframework.boot:spring-boot-starter-web")
        "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin")
        "implementation"("org.jetbrains.kotlin:kotlin-reflect")
        "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        "implementation"("org.springframework.boot:spring-boot-starter-data-neo4j")
        "compileOnly"("org.projectlombok:lombok")
        "annotationProcessor"("org.projectlombok:lombok")
        "testImplementation"("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

project(":domain") {
    tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        enabled = false
    }

    tasks.named<Jar>("jar") {
        enabled = true
    }

    dependencies {
    }
}

project(":infra") {
    tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        enabled = false
    }

    tasks.named<Jar>("jar") {
        enabled = true
    }

    dependencies {
        "implementation"(project(":domain"))
    }
}

project(":web") {
    tasks.named<Jar>("jar") {
        enabled = false
    }

    dependencies {
        "implementation"(project(":domain"))
        "runtimeOnly"(project(":infra"))
    }
}
