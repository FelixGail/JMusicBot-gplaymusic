import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.github.ben-manes.versions") version Plugin.VERSIONS
    kotlin("jvm") version Plugin.KOTLIN
    id("com.github.johnrengelman.shadow") version Plugin.SHADOW_JAR
}

group = "com.github.felixgail"
version = "0.3.0"

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf(
                "-Xuse-experimental=kotlin.Experimental"
            )
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {
    compileOnly(
        group = "com.github.bjoernpetersen",
        name = "musicbot",
        version = Lib.MUSICBOT
    ) {
        isChanging = Lib.MUSICBOT.contains("SNAPSHOT")
    }

    implementation(
        group = "com.github.bjoernpetersen",
        name = "musicbot-youtube",
        version = Lib.YOUTUBE_PROVIDER
    ) {
        isChanging = Lib.YOUTUBE_PROVIDER.contains("SNAPSHOT")
    }
    implementation(
        group = "com.github.felixgail",
        name = "gplaymusic",
        version = Lib.GPLAYMUSIC
    )

    testImplementation(
        group = "com.github.bjoernpetersen",
        name = "musicbot",
        version = Lib.MUSICBOT
    )
    testImplementation(
        group = "org.junit.jupiter",
        name = "junit-jupiter-api",
        version = Lib.JUNIT
    )
    testRuntimeOnly(
        group = "org.slf4j",
        name = "slf4j-simple",
        version = Lib.SLF4J
    )
    testRuntimeOnly(
        group = "org.junit.jupiter",
        name = "junit-jupiter-engine",
        version = Lib.JUNIT
    )
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(1, TimeUnit.MINUTES)
}

repositories {
    jcenter()
    maven("https://oss.sonatype.org/content/repositories/snapshots") {
        mavenContent {
            snapshotsOnly()
        }
    }
}
