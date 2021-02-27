package com.bruce.mygithub.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bruce.mygithub.base.viewmodel.BaseViewModel
import com.bruce.mygithub.base.viewmodel.ErrorState
import com.bruce.mygithub.base.viewmodel.LoadState
import com.bruce.mygithub.base.viewmodel.SuccessState
import com.bruce.mygithub.ext.errorToast

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/25
 *     desc  : BaseActivity + ViewModel
 * </pre>
 */
abstract class BaseVMActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout()
    }

    open fun setContentLayout() {
        setContentView(getLayoutId())
        initViewModelAction()
        initView()
        initData()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    open fun initData() {

    }

    abstract fun getViewModel(): BaseViewModel

    protected fun initViewModelAction() {
        getViewModel().let { baseViewModel ->
            baseViewModel.mStateLiveData.observe(this, { state ->
                when (state) {
                    is LoadState -> showLoading()
                    is SuccessState -> dismissLoading()
                    is ErrorState -> {
                        dismissLoading()
                        state.msg?.run {
                            errorToast(this)
                            handleError()
                        }
                    }
                }
            })
        }
    }

    open fun showLoading() {}

    open fun dismissLoading() {}

    open fun handleError() {}

}