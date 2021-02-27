package com.bruce.mygithub.ext

import android.widget.Toast
import com.bruce.mygithub.AppContext
import es.dmoral.toasty.Toasty

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/26
 *     desc  : Toast拓展函数
 * </pre>
 */


fun infoToast(success: String) {
    Toasty.info(AppContext, success, Toast.LENGTH_SHORT, true).show()
}

fun infoToast(success: Int) {
    Toasty.info(AppContext, success, Toast.LENGTH_SHORT, true).show()
}

fun successToast(success: String) {
    Toasty.success(AppContext, success, Toast.LENGTH_SHORT, true).show()
}

fun successToast(success: Int) {
    Toasty.success(AppContext, success, Toast.LENGTH_SHORT, true).show()
}

fun warningToast(warning: String) {
    Toasty.warning(AppContext, warning, Toast.LENGTH_SHORT, true).show()
}

fun warningToast(warning: Int) {
    Toasty.warning(AppContext, warning, Toast.LENGTH_SHORT, true).show()
}

fun errorToast(error: String) {
    Toasty.error(AppContext, error, Toast.LENGTH_SHORT, true).show();
}

fun errorToast(error: Int) {
    Toasty.error(AppContext, error, Toast.LENGTH_SHORT, true).show();
}