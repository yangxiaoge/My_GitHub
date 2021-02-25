// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    val kotlin_version by extra("1.4.21")
    repositories {
        PluginResource.getAll().forEach {
            maven(it)
        }
        google()
        jcenter()
    }
    dependencies {
        classpath(Libs.gradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        PluginResource.getAll().forEach {
            maven(it)
        }
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}