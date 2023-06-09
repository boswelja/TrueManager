plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.detekt)
}

android {
    namespace = "com.boswelja.truemanager"

    defaultConfig {
        applicationId = "com.boswelja.truemanager"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lint {
        sarifReport = true
        htmlReport = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvmToolchain(17)
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom("$rootDir/config/detekt.yml")
    basePath = rootDir.absolutePath
}

dependencies {
    implementation(projects.core.api)
    implementation(projects.core.composeMenuprovider)

    implementation(projects.features.apps.ui)
    implementation(projects.features.auth)
    implementation(projects.features.dashboard.ui)
    implementation(projects.features.reporting)
    implementation(projects.features.storage)

    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.koin.android)

    // Compose
    implementation(libs.androidx.navigation)
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.compose.tooling)
    androidTestImplementation(libs.compose.ui.test.junit4)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}