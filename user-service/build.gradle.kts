plugins {
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.7"
	java
}
group = "com.project"
version = "0.0.1-SNAPSHOT"
description = "Spring Boot Project"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	runtimeOnly("org.postgresql:postgresql")
	implementation(project(":shared-events"))
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.modelmapper:modelmapper:3.2.4")
	implementation("org.springframework.kafka:spring-kafka")

}

//tasks.withType<Test> {
//	useJUnitPlatform()
//}
