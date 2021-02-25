package com.bruce.mygithub

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.afollestad.assent.AssentResult
import com.afollestad.assent.Callback
import com.afollestad.assent.Permission
import com.bruce.mygithub.base.activity.BaseDataBingActivity
import com.bruce.mygithub.constant.Settings
import com.bruce.mygithub.databinding.ActivityWelcomeBinding
import com.bruce.mygithub.ext.otherwise
import com.bruce.mygithub.ext.runWithPermissions
import com.bruce.mygithub.ext.yes
import com.bruce.mygithub.user.dao.UserDao
import com.jaredrummler.android.widget.AnimatedSvgView
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/24
 *     desc  : 欢迎页
 * </pre>
 */
class WelcomeActivity : BaseDataBingActivity<ActivityWelcomeBinding>() {
    private val userDao: UserDao by inject()

    override fun getLayoutId(): Int = R.layout.activity_welcome

    override fun initView() {
        mAnimatedSvgView.setViewportSize(512f, 512f)
        mAnimatedSvgView.setOnStateChangeListener {
            (it == AnimatedSvgView.STATE_FINISHED).yes {
                askForPermission()
            }
        }
        mAnimatedSvgView.start()
    }

    override fun initData() {
        mDataBind.versionName = BuildConfig.VERSION_NAME
    }

    /**
     * 动态权限申请
     */
    private fun askForPermission() {
        runWithPermissions(
            Permission.READ_PHONE_STATE,
            Permission.READ_EXTERNAL_STORAGE,
            Permission.WRITE_EXTERNAL_STORAGE,
            granted = object : Callback {
                override fun invoke(result: AssentResult) {
                    checkIsLogin()
                }

            },
            denied = object : Callback {
                override fun invoke(result: AssentResult) {
                    Toast.makeText(this@WelcomeActivity, "权限不足", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        )
    }

    /**
     * 登录状态检查
     */
    private fun checkIsLogin() {
        lifecycleScope.launch {
            delay(500)
            val userList = userDao.getAll()
            userList.isEmpty().yes {
//                go2Activity(LoginActivity::class.java)
            }.otherwise {
                Settings.Account.loginUser = userList[0].login
//                go2Activity(HomeActivity::class.java)
            }
        }
    }

    private fun go2Activity(clazz: Class<*>) {
        Intent(this@WelcomeActivity, clazz).run {
            startActivity(this)
            finish()
        }
    }
}