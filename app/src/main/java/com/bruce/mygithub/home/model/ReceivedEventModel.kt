package com.bruce.mygithub.home.model

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/26
 *     desc  : 收到的事件Model
 * </pre>
 */
data class ReceivedEventModel(
    val id: String,
    val type: String,
    val actor: Actor,
    val repo: Repo,
    val created_at: String
)

data class Actor(
    val login: String,
    val display_login: String,
    val avatar_url: String
)

data class Repo(val name: String)
