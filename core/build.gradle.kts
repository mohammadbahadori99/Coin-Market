import dependencies.Libs


plugins {
    GradlePluginId.apply {
        id(ANDROID_LIBRARY)
        id(KOTLIN_ANDROID)
        id(PARCELIZE)
        id(KOTLIN_KAPT)
        id(KOTLIN_Hilt)
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

            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://pro-api.coinmarketcap.com\""
            )
            buildConfigField("String", "DATABASE_NAME", "\"messenger\"")
        }
        android.buildFeatures.dataBinding = true
        compileOptions.sourceCompatibility = JavaVersion.VERSION_1_8
        compileOptions.targetCompatibility = JavaVersion.VERSION_1_8
        testOptions.animationsDisabled = true
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

//
    api(project(":base"))
    implementation(Libs.AndroidX.Room.core)
    implementation(Libs.DependencyInjection.Hilt.android)
    kapt(Libs.DependencyInjection.Hilt.compiler)
    implementation(Libs.AndroidX.paging)
    implementation(Libs.Network.OkHttp.core)
    implementation(Libs.Network.OkHttp.logger)
    implementation(Libs.Network.Retrofit.core)
    implementation(Libs.Network.Retrofit.gsonConverter)
    implementation("io.coil-kt:coil:1.3.2")
    implementation("io.coil-kt:coil-svg:1.3.2")
    Libs.AndroidX.LifeCycle.run {
        implementation(commonJava8)
        implementation(liveData)
        implementation(runtime)
        implementation(viewModel)
    }
    implementation(Libs.AndroidX.extensionsCore)
    implementation(Libs.Kotlin.kotlin_stdlib)
    implementation(Libs.AndroidX.LifeCycle.lifecycle_extensions)
    implementation(Libs.Kotlin.Coroutine.core)
    implementation(Libs.Kotlin.Coroutine.android)
    implementation(Libs.Stetho.okHttp)
    implementation(Libs.Stetho.core)

    implementation(Libs.AndroidX.Navigation.core)
    implementation(Libs.AndroidX.Navigation.fragment)
    implementation(Libs.AndroidX.Navigation.ui)
    implementation(Libs.AndroidX.Navigation.uiKtx)

    kapt(Libs.AndroidX.Room.compiler)
    Libs.AndroidX.DataStore.run {
        implementation(core)
        implementation(preferences)
    }

}