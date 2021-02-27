package com.bruce.mygithub.user.model

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/26
 *     desc  : 用户信息model，供布局文件绑定使用
 * </pre>
 */
data class UserInfoModel(
    val login: String,
    val email: String,
    val bio: String,
    val blog: String,
    val followers: Int,
    val following: Int,
    val public_repos: Int,
    val public_gists: Int,
    val location: String
)
