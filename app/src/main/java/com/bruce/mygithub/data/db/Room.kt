package com.bruce.mygithub.data.db

import androidx.room.Room
import com.bruce.mygithub.AppContext

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/24
 *     desc  :
 * </pre>
 */

private const val DB_NAME = "my_github_db"

val room = Room.databaseBuilder(AppContext, AppDataBase::class.java, DB_NAME).build()

val userDao = room.getUserDao()