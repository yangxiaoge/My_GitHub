package com.bruce.mygithub.user.model

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/26
 *     desc  : 授权请求Model
 * </pre>
 */
data class AuthorizationReqModel(
    val client_secret: String,
    val scopes: List<String>,
    val note: String,
    val note_url: String
)
