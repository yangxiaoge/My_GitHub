package com.bruce.mygithub.util

import android.content.Context
import com.bruce.mygithub.AppContext
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/24
 *     desc  : 基于属性代理封装SharedPreferences
 * </pre>
 */
class Preference<T>(private val key: String, private val defaultValue: T) :
    ReadWriteProperty<Any?, T> {

    companion object {
        private const val SHARE_PRE_NAME = "my_github"

        private val mPreferences by lazy {
            AppContext.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE)
        }

        fun clear() {
            mPreferences.edit().clear().apply()
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(key, value)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(key, defaultValue)
    }

    private fun findPreference(key: String, defaultValue: T): T {
        return with(mPreferences) {
            when (defaultValue) {
                is Int -> getInt(key, defaultValue)
                is Long -> getLong(key, defaultValue)
                is Boolean -> getBoolean(key, defaultValue)
                is String -> getString(key, defaultValue)
                is Float -> getFloat(key, defaultValue)
                else -> throw IllegalArgumentException("This type can't be saved into SharedPreferences")
            } as T
        }
    }

    private fun putPreference(key: String, value: T) {
        mPreferences.edit().run {
            when (value){
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
                is Float -> putFloat(key, value)
                else -> throw IllegalArgumentException("This type can't be saved into SharedPreferences")
            }
        }.apply()
    }

}