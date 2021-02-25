package com.bruce.mygithub

import android.app.Application
import android.content.ContextWrapper
import androidx.core.content.ContextCompat
import com.bruce.mygithub.constant.Configs
import com.bruce.mygithub.di.appModule
import com.jeremyliao.liveeventbus.LiveEventBus
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.tencent.bugly.crashreport.CrashReport
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/24
 *     desc  : App入口
 * </pre>
 */
lateinit var mApplication: Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        mApplication = this

        initBugly()

        initKoin()

        initLiveEventBus()

        initSmartRefreshLayout()

    }

    private fun initBugly() {
        CrashReport.initCrashReport(mApplication, Configs.BUGLY_APP_ID, false)
    }

    private fun initKoin() {
        // start Koin! (https://insert-koin.io/)
        startKoin {
            // declare used Android context
            androidContext(mApplication)
            // declare modules
            modules(appModule)
        }
    }

    private fun initLiveEventBus(){
        LiveEventBus.get()
            .config()
            .supportBroadcast(mApplication)
            .lifecycleObserverAlwaysActive(true)
            .autoClear(false)
    }

    private fun initSmartRefreshLayout(){
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setEnableHeaderTranslationContent(false)
            MaterialHeader(context).setColorSchemeColors(
                ContextCompat.getColor(
                    context,
                    R.color.purple_200
                )
            )
        }
    }

}

object AppContext : ContextWrapper(mApplication)//ContextWrapper对Context上下文进行包装(装饰者模式)
