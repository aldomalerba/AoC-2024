plugins {
    kotlin("jvm") version "2.0.21"
}

repositories {
    mavenCentral()
}

sourceSets {
    main {
        kotlin.srcDir("src/main")
    }
    test {
        kotlin.srcDir("src/test")
    }
}


dependencies {
    testImplementation(kotlin("test"))
}

tasks {
    wrapper {
        gradleVersion = "8.11"
    }
    test {
        useJUnitPlatform()
    }
}
