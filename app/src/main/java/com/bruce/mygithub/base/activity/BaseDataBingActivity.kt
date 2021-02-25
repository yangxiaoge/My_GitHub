package com.bruce.mygithub.base.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/25
 *     desc  : BaseDataBingActivity
 * </pre>
 */
abstract class BaseDataBingActivity<DB : ViewDataBinding> : BaseActivity() {
    lateinit var mDataBind: DB

    override fun setContentLayout() {
        //BaseDataBingActivity需要重写，无需继承父类
        //super.setContentLayout()
        mDataBind = DataBindingUtil.setContentView(this, getLayoutId())
        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDataBind.unbind()
    }
}