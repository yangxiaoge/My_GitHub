package com.bruce.mygithub.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bruce.mygithub.user.dao.UserDao
import com.bruce.mygithub.user.model.db.User

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/24
 *     desc  : 定义数据库
 * </pre>
 */

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}