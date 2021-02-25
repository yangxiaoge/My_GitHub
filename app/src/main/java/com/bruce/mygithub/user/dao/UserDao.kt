package com.bruce.mygithub.user.dao

import androidx.room.*
import com.bruce.mygithub.user.model.db.User

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/24
 *     desc  : UserDao
 * </pre>
 */
@Dao
interface UserDao {
    @Query("select * from User")
    suspend fun getAll(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user: User) //vararg：可变长度参数

    @Delete
    suspend fun deleteAll(vararg user:User)
}