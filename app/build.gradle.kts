plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.numberfacts"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.numberfacts"
        minSdk = 23
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets["main"].res.srcDirs("src/main/res", "src/main/res/icon")
    buildFeatures.viewBinding = true
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    val room = "2.4.3"
    val navigation = "2.5.3"
    val coreKtx = "1.9.0"
    val appCompat = "1.5.1"
    val material = "1.7.0"
    val rxKotlin = "3.0.1"
    val rxAndroid = "3.0.2"
    val dagger = "2.44.2"
    val retrofit = "2.9.0"
    val lifecycle = "2.5.1"
    val gson = "2.10"
    val viewBindingDelegate = "1.5.6"
    implementation("androidx.core:core-ktx:$coreKtx")
    implementation("androidx.appcompat:appcompat:$appCompat")
    implementation("com.google.android.material:material:$material")
    implementation("io.reactivex.rxjava3:rxkotlin:$rxKotlin")
    implementation("io.reactivex.rxjava3:rxandroid:$rxAndroid")
    implementation("androidx.room:room-rxjava3:$room")
    implementation("androidx.room:room-runtime:$room")
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.google.dagger:dagger:$dagger")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle")
    implementation("com.squareup.retrofit2:adapter-rxjava3:$retrofit")
    implementation("com.google.code.gson:gson:$gson")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigation")
    implementation("androidx.navigation:navigation-ui-ktx:$navigation")
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:$viewBindingDelegate")
    kapt("androidx.room:room-compiler:$room")
    kapt("com.google.dagger:dagger-compiler:$dagger")

}