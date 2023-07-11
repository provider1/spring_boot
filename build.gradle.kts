import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.13"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "c.e"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}
configurations {
	all {
		exclude("org.springframework.boot","spring-boot-starter-logging")
	}
}

dependencies {
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	runtimeOnly("com.mysql:mysql-connector-j")

	implementation ("org.springframework.boot:spring-boot-starter-log4j2")

	implementation("com.mysql:mysql-connector-j:8.0.32")


	implementation("org.mariadb.jdbc:mariadb-java-client:3.1.2")


	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

	//Testing
	testImplementation("org.mockito.kotlin:mockito-kotlin:5.0.0")

	implementation("org.springframework.security:spring-security-crypto:5.7.5")

	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

// customize authorization server
	implementation("org.springframework.cloud:spring-cloud-starter-oauth2:2.2.5.RELEASE")
//	import("org.springframework.cloud:spring-cloud-dependencies:2022.0.0")
	implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:4.0.0")

// https://mvnrepository.com/artifact/javax.persistence/javax.persistence-api
//	implementation("javax.persistence:javax.persistence-api:2.2")

	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")

	implementation("org.apache.httpcomponents.client5:httpclient5:5.2.1")


	implementation("com.h2database:h2:2.1.214")


}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
