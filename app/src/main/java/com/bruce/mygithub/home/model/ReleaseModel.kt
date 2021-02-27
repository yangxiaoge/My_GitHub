package com.bruce.mygithub.home.model

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/26
 *     desc  : 发布Model
 * </pre>
 */
data class ReleaseModel(
    val tag_name: String,
    val assets: List<AssetsModel>
)

data class AssetsModel(val browser_download_url: String)
