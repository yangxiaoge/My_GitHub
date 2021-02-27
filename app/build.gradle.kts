import ProductFlavors.coolApk
import ProductFlavors.github
import ProductFlavors.googlePlay
import ProductFlavors.others

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
    id("kotlin-android")
}

android {
    compileSdkVersion(Apps.compileSdk)
    buildToolsVersion(Apps.buildTools)

    defaultConfig {
        applicationId(Apps.applicationId)
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
        versionCode(Apps.versionCode)
        versionName(Apps.versionName)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create(SigningConfigs.Release) {
            storeFile = file("yangxiaoge.jks")
            storePassword = "bruce123456"
            keyAlias = "bruce"
            keyPassword = "bruce123456"
            isV1SigningEnabled = true
            isV2SigningEnabled = true
        }
    }

    buildTypes {
        getByName(BuildTypes.Debug) {
            isMinifyEnabled = false
            applicationIdSuffix = ".${BuildTypes.Debug}"
            versionNameSuffix = ".${BuildTypes.Debug}"
            isDebuggable = true
            signingConfig = this@android.signingConfigs.getByName(SigningConfigs.Release)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName(BuildTypes.Release) {
            isMinifyEnabled = true
            isShrinkResources = true
            isZipAlignEnabled = true
            signingConfig = this@android.signingConfigs.getByName(SigningConfigs.Release)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions("version")

    productFlavors {
        create(googlePlay)
        create(github)
        create(coolApk)
        create(others) {
            isDefault = true
        }
    }

    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                output.outputFileName = "mygithub-v${variant.versionName}.apk"
            }
    }

    kapt{
        arguments{
            arg("room.schemaLocation","$projectDir/schemas")
            arg("room.incremental",true)
            arg("room.expandProjection",true)
        }
    }

    buildFeatures {
        dataBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlin)
    implementation(Libs.appcompat)
    implementation(Libs.material)
    implementation(Libs.recycleView)
    implementation(Libs.constraintLayout)
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation(TestLibs.junit)

    //协程
    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)

    //Android KTX 是一组 Kotlin 扩展程序，属于 Android Jetpack 系列
    implementation(Libs.coreKtx)
    implementation(Libs.lifecycleRuntime)
    implementation(Libs.lifecycleViewModel)
    implementation(Libs.lifecycleLiveData)

    //okhttp + retrofit(2.6.0以后支持协程)
    implementation(Libs.okhttp3LoggingInterceptor)
    implementation(Libs.retrofit2)
    implementation(Libs.retrofit2ConverterGson)

    //room
    implementation(Libs.roomRuntime)
    kapt(Libs.roomCompiler)
    implementation(Libs.roomKtx)

    //navigation
    implementation(Libs.navigationFgKtx)
    implementation(Libs.navigationUIKtx)

    //paging
    implementation(Libs.pagingRuntimeKtx)

    //WorkManager
    implementation(Libs.workRuntimeKtx)

    //koin(依赖注入)
    implementation(Libs.koinScope)
    implementation(Libs.koinViewmodel)
    implementation(Libs.koinExt)

    //动态权限
    implementation(Libs.afollestad)

    //其他三方控件
    implementation(Libs.timber)
    implementation("de.hdodenhof:circleimageview:3.0.0")
    implementation("com.just.agentweb:agentweb:4.0.3-beta")
    implementation("com.github.bumptech.glide:glide:4.11.0")
    kapt("com.github.bumptech.glide:compiler:4.11.0")
    implementation("com.github.GrenderG:Toasty:1.4.2")
    implementation("com.github.jd-alexander:LikeButton:0.2.3")
    implementation("com.github.Kennyc1012:MultiStateView:2.1.2")
    implementation("com.jaredrummler:animated-svg-view:1.0.6")
    implementation("com.jeremyliao:live-event-bus-x:1.4.4")
    implementation("com.github.nitrico.lastadapter:lastadapter:2.3.0")
    implementation("com.scwang.smartrefresh:SmartRefreshLayout:1.1.0")
    implementation("com.scwang.smartrefresh:SmartRefreshHeader:1.1.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.lxj:xpopup:1.8.10-x")
    implementation("com.github.chrisbanes:PhotoView:2.3.0@aar")

    //bugly捕获异常
    implementation("com.tencent.bugly:crashreport:3.2.1")

    //implementation project(path: ":simplebehavior")

}
