package com.bruce.mygithub.ext

import android.app.Activity
import android.content.pm.PackageManager
import com.afollestad.assent.*
import com.afollestad.assent.rationale.RationaleHandler

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/24
 *     desc  : 拓展Assent动态申请权限
 * </pre>
 */

fun Activity.runWithPermissions(
    vararg permissions: Permission,
    requestCode: Int = 40,
    rationaleHandler: RationaleHandler? = null,
    granted: Callback,
    denied: Callback
) {
    isAllGranted(*permissions).yes {
        val permissionList = permissions.asList()
        val grantResultList = IntArray(permissions.size)
        permissionList.forEachIndexed { index, _ ->
            grantResultList[index] = PackageManager.PERMISSION_GRANTED
        }
        granted.invoke(AssentResult(permissionList, grantResultList))
    }.otherwise {
        askForPermissions(
            *permissions,
            requestCode = requestCode,
            rationaleHandler = rationaleHandler,
            callback = {
                it.isAllGranted(*permissions).yes {
                    granted.invoke(it)
                }.otherwise {
                    denied.invoke(it)
                }
            }
        )
    }
}