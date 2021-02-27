package com.bruce.mygithub.data.http

import android.util.Base64
import com.bruce.mygithub.constant.Constant
import com.bruce.mygithub.constant.Settings
import com.bruce.mygithub.ext.isLogin
import com.bruce.mygithub.ext.otherwise
import com.bruce.mygithub.ext.yes
import okhttp3.Interceptor
import okhttp3.Response

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/26
 *     desc  :
 * </pre>
 */
class OAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return with(request) {
            this.url.pathSegments.contains(Constant.DOWNLOAD).yes {
                //下载Apk，无需添加请求头
                chain.proceed(this)
            }.otherwise {
                chain.proceed(
                    request.newBuilder().apply {
                        when {
                            //登陆授权/退出接口
                            request.url.pathSegments.contains(Constant.AUTHORIZATIONS) -> {
                                val userCredentials =
                                    "${Settings.Account.userName}:${Settings.Account.password}"
                                val auth = "basic ${
                                    Base64.encodeToString(
                                        userCredentials.toByteArray(),
                                        Base64.NO_WRAP
                                    )
                                }"
                                addHeader(Constant.AUTHORIZATION, auth)
                            }
                            //登录了
                            isLogin() -> {
                                val auth = "Token ${Settings.Account.token}"
                                addHeader(Constant.AUTHORIZATION, auth)
                            }
                        }
                    }.build()
                )
            }
        }
    }
}