import dependencies.Libs

plugins {
    GradlePluginId.apply {
        id(ANDROID_LIBRARY)
        id(KOTLIN_ANDROID)
        id(PARCELIZE)
        id(KOTLIN_KAPT)
    }
}
kapt {
    correctErrorTypes = true
    useBuildCache = true

    javacOptions {
        option("-Xmaxerrs", 2000)
    }
}
android {
    GradleVersionConfig.apply {
        compileSdkVersion(COMPILE_SDK_VERSION)
        buildToolsVersion = BUILD_TOOLS_VERSION

        defaultConfig {
            minSdkVersion(MIN_SDK_VERSION)
            targetSdkVersion(TARGET_SDK_VERSION)
            versionCode = 1
            versionName = "1.0"
            testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
        }
        compileOptions.sourceCompatibility = JavaVersion.VERSION_1_8
        compileOptions.targetCompatibility = JavaVersion.VERSION_1_8
        testOptions.animationsDisabled = true
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}


/**
 *  domain module should not depend on framework.
 *  it should depend on language (in our case kotlin and java).
 *  but there is no way to use paging v3 and don't use android dependencies
 *  so unfortunately i have to api paging and core from android framework.
 */
dependencies {
    implementation(Libs.Kotlin.Coroutine.core)
    api("androidx.paging:paging-runtime-ktx:3.0.0")
    //androidx.core is needed for above dependency (paging)
    api("androidx.core:core-ktx:1.6.0")
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")
    implementation(project(":base"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}