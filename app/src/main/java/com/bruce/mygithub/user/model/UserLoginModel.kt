package com.bruce.mygithub.user.model

import androidx.databinding.ObservableField

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/25
 *     desc  :
 * </pre>
 */
data class UserLoginModel(
    val userName: ObservableField<String> = ObservableField<String>(""),
    val pwd: ObservableField<String> = ObservableField<String>("")
)
