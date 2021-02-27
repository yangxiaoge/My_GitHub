import kotlin.reflect.full.memberProperties

object Apps {
    const val compileSdk = 30
    const val buildTools = "30.0.3"
    const val minSdk = 21
    const val targetSdk = 30
    const val applicationId = "com.bruce.mygithub"
    const val versionCode = 1
    const val versionName = "1.0"
}

object SigningConfigs{
    const val Release = "Release"
}

object BuildTypes {
    const val Release = "release"
    const val Debug = "debug"
}

object ProductFlavors {
    const val googlePlay = "GooglePlay"
    const val coolApk = "CoolApk"
    const val github = "Github"
    const val others = "others"
}

object Versions {
    const val gradle = "4.1.2"

    const val kotlin = "1.4.21" //1.4.21

    const val coreKtx = "1.3.2"

    const val coroutines = "1.3.3"

    const val appcompat = "1.2.0"

    const val lifeCycle = "2.2.0"

    const val material = "1.3.0"

    const val constraintLayout = "2.0.4"

    const val recyclerView = "1.1.0"

    const val junit = "4.13.2"

    const val retrofit2 = "2.7.1"

    const val okhttp3LoggingInterceptor = "4.0.0"

    const val room = "2.2.5"

    const val navigation = "2.3.2"

    const val paging = "2.1.2"

    const val workmanager = "2.3.4"

    const val koin = "2.0.1"

    const val afollestad = "2.3.1"

    const val timber = "4.6.0"

}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"

    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"

    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"

    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val material = "com.google.android.material:material:${Versions.material}"

    const val recycleView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    const val okhttp3LoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3LoggingInterceptor}"

    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"

    const val retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"

    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val navigationFgKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"

    const val navigationUIKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val pagingRuntimeKtx = "androidx.paging:paging-runtime-ktx:${Versions.paging}"

    const val workRuntimeKtx = "androidx.work:work-runtime-ktx:${Versions.workmanager}"

    const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"

    const val koinViewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    const val koinExt = "org.koin:koin-androidx-ext:${Versions.koin}"

    const val afollestad = "com.afollestad:assent:${Versions.afollestad}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
}

object PluginResource {
    // All consts are accessed via reflection
    const val ALi_central = "https://maven.aliyun.com/repository/central"
    const val ALi_public = "https://maven.aliyun.com/repository/public"
    const val ALi_jcenter = "https://maven.aliyun.com/repository/public"
    const val ALi_google = "https://maven.aliyun.com/repository/google"
    const val ALi_gradle_plugin = "https://maven.aliyun.com/repository/gradle-plugin"

    const val github = "https://jitpack.io"

    // positive" function can not be private"
    fun getAll() = PluginResource::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()
}