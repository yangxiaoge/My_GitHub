package com.bruce.mygithub.ext

import android.app.Activity
import android.content.Intent
import android.net.Uri

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/24
 *     desc  : Activity拓展函数
 * </pre>
 */

/**
 * 打开指定链接
 * @param url 链接地址
 */
fun Activity.openLink(url: String) {
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
        startActivity(this)
    }
}