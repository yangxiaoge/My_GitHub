package com.bruce.mygithub.repos.model

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/26
 *     desc  : 仓库Model
 * </pre>
 */
data class ReposItemModel(
    val name: String,
    val description: String,
    val owner: ReposOwnerModel,
    val language: String,
    val stargazers_count: Long,
    val forks: Long,
    val html_url: String
)
