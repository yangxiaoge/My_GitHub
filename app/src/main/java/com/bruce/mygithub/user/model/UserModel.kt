package com.bruce.mygithub.user.model

import java.io.Serializable

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/26
 *     desc  : 用户Model
 * </pre>
 */
data class UserModel(val login: String, val avatar_url: String, val id: Int = 0) : Serializable
