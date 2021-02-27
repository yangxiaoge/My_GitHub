package com.bruce.mygithub.ext

import com.bruce.mygithub.constant.Settings

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/26
 *     desc  :
 * </pre>
 */

//登录了
fun isLogin() = Settings.Account.token.isNotBlank()