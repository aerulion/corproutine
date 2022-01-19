plugins {
    `java-library`
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/aerulion/nucleus")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.token") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
    maven {
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")
    compileOnly("net.aerulion:nucleus:2.0.0")
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    compileOnly("org.jetbrains:annotations:22.0.0")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}

group = "net.aerulion"
version = "2.2.0"

bukkit {
    name = "CorpRoutine"
    main = "net.aerulion.corproutine.Main"
    version = getVersion().toString()
    author = "aerulion"
    apiVersion = "1.18"
    depend = listOf("Nucleus")
    commands {
        register("routine") {
            description = "Öffnet das Routine-Aufgaben-Menü."
        }
    }
    permissions {
        register("corproutine.use") {
            default = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.Permission.Default.OP
        }
        register("corproutine.alet") {
            default = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.Permission.Default.OP
        }
    }
}
