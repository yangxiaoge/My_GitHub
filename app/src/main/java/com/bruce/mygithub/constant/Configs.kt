package com.bruce.mygithub.constant

import android.os.Build
import java.util.*

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/24
 *     desc  : 统一配置
 * </pre>
 */
object Configs {
    //Github Base Url
    const val GITHUB_BASE_URL = "https://github.com/"

    //分页数量
    const val PAGE_SIZE = 20

    //开发者信息
    const val OWNER = "yangxiaoge"
    const val OWNER_REPO = "My_GitHub"

    //应用登陆授权
    const val CLIENT_ID = "712c6ce8ae9ed318978a"
    const val CLIENT_SECRET = "9790077ead02da79305b3eadd922db59a289174e"
    const val NOTE = "my_github"
    const val NOTE_URL = "https://github.com/yangxiaoge"
    val SCOPE = listOf("user", "repo", "notifications", "gist")
    const val OAUTH2_SCOPE = "user,repo,gist,notifications"
    val FINGERPRINT: String by lazy { Build.FINGERPRINT + UUID.randomUUID().toString() }

    //bugly
    const val BUGLY_APP_ID = "69d3cc1adf"
}