plugins {
    alias(libs.plugins.android.library)

    // DI
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)

    // Firebase
    alias(libs.plugins.gms.google.services)

    // Serialization
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.fenix.data"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    // Android core
    implementation(libs.androidx.core.ktx)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    // DI
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.authentication)

    // GMS Play services location
    implementation(libs.play.services.location)

    // Retrofit2
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.kotlin.serialization.json)

    // domain
    implementation(project(":domain"))
}