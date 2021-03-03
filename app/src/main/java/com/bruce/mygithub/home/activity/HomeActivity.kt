package com.bruce.mygithub.home.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bruce.mygithub.R
import com.bruce.mygithub.base.activity.BaseVMActivity
import com.bruce.mygithub.base.viewmodel.BaseViewModel
import com.bruce.mygithub.databinding.LayoutNavHeaderBinding
import com.bruce.mygithub.home.viewmodel.HomeViewModel
import com.bruce.mygithub.user.activity.LoginActivity
import com.bruce.mygithub.user.dao.UserDao
import com.bruce.mygithub.user.model.db.User
import com.bruce.mygithub.util.Preference
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseVMActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val mUserDao: UserDao by inject()
    private val mViewModel: HomeViewModel by viewModel()
    private lateinit var mUser: User

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun getViewModel(): BaseViewModel = mViewModel

    override fun initView() {
        setSupportActionBar(mToolbar)
        initUserInfo()
        initNavigationView()
        initDrawerLayout()
    }

    override fun initData() {

    }

    private fun initUserInfo() {
        lifecycleScope.launch {
            mUser = mUserDao.getAll()[0]
            initHeaderLayout()
        }
    }

    private fun initHeaderLayout() {
        val dataBind =
            LayoutNavHeaderBinding.inflate(LayoutInflater.from(this), mNavigationView, false)
        dataBind.user = mUser
        mNavigationView.addHeaderView(dataBind.root)
    }

    private fun initNavigationView() {
        mToolbar.overflowIcon = ContextCompat.getDrawable(this, R.mipmap.icon_search)
        mNavigationView.setNavigationItemSelectedListener(this)
    }

    private fun initDrawerLayout() {
        ActionBarDrawerToggle(
            this, mDrawerLayout, mToolbar,
            R.string.drawer_open, R.string.drawer_close
        )
            .apply {
                syncState()
            }.run {
                mDrawerLayout.addDrawerListener(this)
            }
    }

    override fun onSupportNavigateUp(): Boolean =
        Navigation.findNavController(this, R.id.nav_home_fragment).navigateUp()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_user -> {
                /*go2UserInfoActivity(mUser.login, mUser.avatar_url)*/
            }
            R.id.item_about -> {
                /*Intent(this, AboutActivity::class.java).run { startActivity(this) }*/
            }
            R.id.item_logout -> {
                /*showLogoutPopup()*/
            }
        }

        //选择菜单时，关闭侧滑菜单
        mDrawerLayout.closeDrawers()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)//ToolBar设置菜单按钮
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_search_repos -> {
                /*go2SearchActivity(true)*/
            }
            R.id.item_search_users -> {
                /*go2SearchActivity(false)*/
            }
        }
        return true
    }

    private fun logout() {
        Preference.clear()
        mViewModel.deleteUser()
        Intent(this, LoginActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}