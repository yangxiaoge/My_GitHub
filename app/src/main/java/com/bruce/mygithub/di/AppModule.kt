package com.bruce.mygithub.di

import com.bruce.mygithub.data.db.userDao
import com.bruce.mygithub.data.http.UserService
import com.bruce.mygithub.user.api.UserApi
import com.bruce.mygithub.user.repository.UserRepository
import com.bruce.mygithub.user.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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

    viewModel { UserViewModel(get()) }
}

val reposModule = module {
    //todo

    //factory 每次注入时都重新创建一个新的对象
    factory { UserRepository(get(), get()) }
}

val remoteModule = module {
    //todo
    //single 单列注入
    single<UserApi> { UserService }
}

val localModule = module {
    //单例注入userDao-用户数据访问对象
    single { userDao }
}

val appModules = listOf(viewModelModule, reposModule, remoteModule, localModule)

