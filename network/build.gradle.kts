plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Project.compileSdk

    defaultConfig {
        minSdk = Project.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.androidCore)

    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.moshiAdapters)
    implementation(Dependencies.moshiKotlin)
    implementation(Dependencies.retrofitConverter)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.okhttpInterceptor)
    implementation(Dependencies.javaxInject)

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitTest)
    androidTestImplementation(Dependencies.espressoCore)
}