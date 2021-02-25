package com.bruce.mygithub.constant

import com.bruce.mygithub.util.Preference

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/24
 *     desc  : 设置，账户信息SP存储读取
 * </pre>
 */
object Settings {

    object Account {
        var token by Preference(Constant.USER_TOKEN, "")
        var loginUser by Preference(Constant.LOGIN_USER, "")
        var userName by Preference(Constant.USER_NAME, "")
        var password by Preference(Constant.USER_PASSWORD, "")
    }

}