plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.mr.anonym.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.roomSQLite)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

//    Google truth for testing (test utils)
    testImplementation(libs.googleTruthTest)
    androidTestImplementation(libs.googleTruthTest)

//    Arch core testing
    testImplementation(libs.archCoreTesting)
    androidTestImplementation(libs.archCoreTesting)

//    Module
    implementation(project(":domain"))

    //    Room SQLite
    implementation(libs.roomSQLite)
    implementation(libs.roomWithCoroutines)
    implementation(libs.roomPaging3)
    kapt(libs.kaptCompiler)

//    Data Store
    implementation(libs.androidx.dataStore)

    //    Retrofit
    implementation(libs.gsonConverter)
    implementation(libs.retrofit2)
}