import java.io.ByteArrayOutputStream

plugins {
    kotlin("jvm") version "1.8.20"
}

fun getVersionName(): Any {
    return try {
        val stdOut = ByteArrayOutputStream()
        exec{
            commandLine = listOf("git","describe","--tags")
            standardOutput = stdOut
        }
        val delimiter = "-"
        stdOut.toString().split(delimiter)[0].replace("\n","")
    }catch (e:Exception){
        ""
    }
}

group = "com.jamie"
version = getVersionName()

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20230618")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}