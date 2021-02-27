package com.bruce.mygithub.user.activity

import android.content.Intent
import android.view.View
import com.bruce.mygithub.HomeActivity
import com.bruce.mygithub.R
import com.bruce.mygithub.base.activity.BaseDataBingVMActivity
import com.bruce.mygithub.base.viewmodel.BaseViewModel
import com.bruce.mygithub.constant.Configs
import com.bruce.mygithub.constant.Settings
import com.bruce.mygithub.databinding.ActivityLoginBinding
import com.bruce.mygithub.ext.otherwise
import com.bruce.mygithub.ext.yes
import com.bruce.mygithub.user.model.UserLoginModel
import com.bruce.mygithub.user.model.UserModel
import com.bruce.mygithub.user.model.db.User
import com.bruce.mygithub.user.viewmodel.UserViewModel
import com.bruce.mygithub.util.BrowserUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/24
 *     desc  : 登录页
 * </pre>
 */
class LoginActivity : BaseDataBingVMActivity<ActivityLoginBinding>() {
    companion object{
        const val TAG = "LoginActivity"
    }

    private val mViewModel: UserViewModel by viewModel()

    private val mUserLoginModel: UserLoginModel by lazy { UserLoginModel() }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun getViewModel(): BaseViewModel = mViewModel

    override fun initView() {
        login_btn.setOnClickListener {
            login()
        }

        oauth_iv.setOnClickListener {
            loginByOAuth()
        }
    }

    override fun initData() {
        mDataBind.userLoginModel = mUserLoginModel
    }

    private fun login() {
        val userName = mUserLoginModel.userName.get().toString()
        val pwd = mUserLoginModel.pwd.get().toString()

        userName.isEmpty().yes {
            mUserNameInputLayout.error = "User name cannot be empty"
            mUserNameInputLayout.isErrorEnabled = true
        }.otherwise {
            mUserNameInputLayout.isErrorEnabled = false

            pwd.isEmpty().yes {
                mPwdInputLayout.error = "Password cannot be empty"
                mPwdInputLayout.isErrorEnabled = true
            }.otherwise {
                mPwdInputLayout.isErrorEnabled = false
                login_btn.visibility = View.GONE
                mOAuthProgress.visibility = View.VISIBLE

                Settings.Account.userName = userName
                Settings.Account.password = pwd

                createOrGetAuthorization()
            }
        }

    }

    private fun loginByOAuth() {
        val randomState = UUID.randomUUID().toString()
        val url = "${Configs.GITHUB_BASE_URL}login/oauth/authorize" +
                "?client_id=" + Configs.CLIENT_ID +
                "&scope=" + Configs.OAUTH2_SCOPE +
                "&state=" + randomState
        BrowserUtil.open(this, url)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            val uri = it.data
            if (uri != null) {
                val code = uri.getQueryParameter("code")
                val state = uri.getQueryParameter("state")
                getToken(code, state)
            }
        }
    }

    private fun createOrGetAuthorization() {
        mViewModel.createOrGetAuthorization().observe(this) {
            Timber.i("createOrGetAuthorization: $it")
            //保存授权后的Token
            Settings.Account.token = it.token
            //获取用户信息
            getUserInfo()
        }
    }

    private fun getToken(code: String?, state: String?) {
        if (code.isNullOrEmpty() || state.isNullOrEmpty()) return
        mOAuthProgress.visibility = View.VISIBLE
        oauth_iv.visibility = View.GONE
        mViewModel.getAccessToken(code, state).observe(this, {
            Timber.i("getToken: $it")
            Settings.Account.token = it.access_token
            getUserInfo()
        })
    }

    private fun getUserInfo() {
        mViewModel.getUser().observe(this) {
            saveUserInfo(it)
        }
    }

    private fun saveUserInfo(userModel: UserModel) {
        User(userModel.id, userModel.login, userModel.avatar_url).apply {
            Timber.i("saveUserInfo: $this")
            Settings.Account.loginUser = this.login
            mViewModel.saveLocalUser(this)
            go2MainActivity()
        }
    }

    private fun go2MainActivity() {
        Intent(this, HomeActivity::class.java).run {
            startActivity(this)
            finish()
        }
    }

    override fun handleError() {
        //login_btn.visibility = View.VISIBLE
        //github只允许oauth方式登录
        oauth_iv.visibility = View.VISIBLE
        mOAuthProgress.visibility = View.GONE
    }
}