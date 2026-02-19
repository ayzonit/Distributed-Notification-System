
plugins {
    id("java")
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.project"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

group = "org.example"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(platform("org.junit:junit-bom:5.10.0"))
    implementation("org.junit.jupiter:junit-jupiter")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
}

tasks.test {
    useJUnitPlatform()
}