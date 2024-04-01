plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.livmas.currencyinfo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.livmas.currencyinfo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":ui"))
    implementation(project(":feature:currency"))
    implementation(project(":data"))

    //DI
    val koinVersion = "3.5.0"
    implementation ("io.insert-koin:koin-android:$koinVersion")

    val ktxVersion = "1.12.0"
    implementation("androidx.core:core-ktx:$ktxVersion")
    val appcompatVersion = "1.6.1"
    implementation("androidx.appcompat:appcompat:$appcompatVersion")
    val materialVersion = "1.11.0"
    implementation("com.google.android.material:material:$materialVersion")
    val constraintVersion = "2.1.4"
    implementation("androidx.constraintlayout:constraintlayout:$constraintVersion")
    val junitVersion = "4.13.2"
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


}