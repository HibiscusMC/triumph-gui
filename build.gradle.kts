plugins {
    id("java-library")
    id("com.github.hierynomus.license") version "0.16.1"
    id("com.github.ben-manes.versions") version "0.44.0"
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

subprojects {

    apply {
        plugin("java-library")
        plugin("com.github.hierynomus.license")
    }

    group = "com.hibiscusmc"
    version = "0.1.0"

    dependencies {
        compileOnly("org.jetbrains:annotations:24.0.0")
        compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
        implementation("commons-lang:commons-lang:2.6")

        val adventureVersion = "4.12.0"
        api("net.kyori:adventure-api:$adventureVersion")
        api("net.kyori:adventure-text-serializer-legacy:$adventureVersion")
        api("net.kyori:adventure-text-serializer-gson:$adventureVersion")
    }

    license {
        header = rootProject.file("LICENSE")
        encoding = "UTF-8"
        mapping("java", "JAVADOC_STYLE")
        include("**/*.java")
    }

    tasks {
        withType<JavaCompile> {
            sourceCompatibility = "17"
            targetCompatibility = "17"
            options.encoding = "UTF-8"
        }
    }

}
