plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = Project.compileSdk

    defaultConfig {
        applicationId = "io.parrotsoftware.qatest"
        minSdk = Project.minSdk
        targetSdk = Project.targetSdk
        versionCode = Project.versionCode
        versionName = Project.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"http://api-staging.parrot.rest\"")
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }

    kapt {
        correctErrorTypes = true
    }

}

dependencies {

    implementation(project(":network"))

    implementation(Dependencies.androidCore)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraint)

    implementation(Dependencies.lifecycleLivedata)
    implementation(Dependencies.lifecycleViewmodel)
    implementation(Dependencies.epoxy)
    implementation(Dependencies.epoxyDatabinding)
    implementation(Dependencies.navigation)
    implementation(Dependencies.navigationUi)
    implementation(Dependencies.swipeRefreshLayout)
    implementation(Dependencies.timber)
    implementation(Dependencies.daggerHilt)
    implementation(Dependencies.splashScreen)
    implementation(Dependencies.glide)
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.room)

    kapt(Dependencies.roomCompiler)
    kapt(Dependencies.daggerHiltCompiler)
    kapt (Dependencies.epoxyProcessor)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockitoInline)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.mockitoCore)
    testImplementation(Dependencies.archCore)

    androidTestImplementation(Dependencies.archCore)
    androidTestImplementation(Dependencies.junitTest)
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(Dependencies.mockitoAndroid)
}