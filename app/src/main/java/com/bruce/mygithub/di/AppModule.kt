package com.bruce.mygithub.di

import com.bruce.mygithub.data.db.userDao
import org.koin.dsl.module


/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/24
 *     desc  : Koin 注入
 *     教程   : https://www.jianshu.com/p/bccb93a78cee?utm_campaign=shakespeare
 * </pre>
 */

val viewModelModule = module {
    //todo
}

val reposModule = module {
    //todo
}

val remoteModule = module {
    //todo
}

val localModule = module {
    //单例注入userDao
    single { userDao }
}

val appModule = listOf(viewModelModule, reposModule, remoteModule, localModule)

