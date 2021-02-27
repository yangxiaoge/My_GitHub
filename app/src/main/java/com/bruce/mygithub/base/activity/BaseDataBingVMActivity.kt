package com.bruce.mygithub.base.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/26
 *     desc  :带有协程基类(DataBinding + ViewModel),使用代理类完成
 * </pre>
 */
abstract class BaseDataBingVMActivity<DB : ViewDataBinding> : BaseVMActivity() {
    lateinit var mDataBind: DB
    override fun setContentLayout() {
        mDataBind = DataBindingUtil.setContentView(this, getLayoutId())
        mDataBind.lifecycleOwner = this
        initViewModelAction()
        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDataBind.unbind()
    }
}