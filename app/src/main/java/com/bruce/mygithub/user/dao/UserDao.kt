package com.bruce.mygithub.user.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bruce.mygithub.user.model.db.User

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/24
 *     desc  : UserDao - 操作数据库的方法，数据访问对象
 * </pre>
 */
@Dao
interface UserDao {
    @Query("select * from User")
    suspend fun getAll(): List<User>

    @Query("select * from User")
    fun getAllLive(): LiveData<List<User>> //当insert,update等操作特定的表 就会触发emitter.onNext()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user: User) //vararg：可变长度参数

    @Delete
    suspend fun deleteAll(vararg user: User)
}