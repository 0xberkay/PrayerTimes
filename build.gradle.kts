plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "1.8.21"
  id("org.jetbrains.intellij") version "1.13.3"
  kotlin("plugin.serialization") version "1.9.10"

}

group = "com.github.x0berkay"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version.set("IC-2023.3.2")
  type.set("IC") // Target IDE Platform

  plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
  }
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
  }

  patchPluginXml {
    sinceBuild.set("233.0") // Adjust this to match the required build number range for compatibility
    untilBuild.set("241.*") // Set the upper limit for the build range
  }


  signPlugin {
    certificateChainFile.set(file("chain.crt"))
    privateKeyFile.set(file("private.pem"))
    password.set("lapazq")

  }

  publishPlugin {
    token.set(System.getenv("TOKEN"))
  }
}
