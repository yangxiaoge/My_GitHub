package com.bruce.mygithub.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
 *     time  : 2021/03/02
 *     desc  : Fragment懒加载
 * </pre>
 */
abstract class BaseVMFragment : Fragment() {

    protected var mRootView: View? = null

    private var mIsHasData = false//是否加载过数据

    lateinit var mActivity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModelAction()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mRootView == null) {
            mRootView = View.inflate(container?.context, getLayoutId(), null)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        lazyLoadData()
    }

    private fun lazyLoadData() {
        if (!mIsHasData) {
            mIsHasData = true
            initData()
        }
    }

    private fun initViewModelAction() {
        this.getViewModel().let { baseViewModel ->
            baseViewModel.mStateLiveData.observe(this, Observer { stateActionState ->
                when (stateActionState) {
                    LoadState -> showLoading()
                    SuccessState -> dismissLoading()
                    is ErrorState -> {
                        dismissLoading()
                        stateActionState.msg?.apply {
                            errorToast(this)
                            handleError()
                        }
                    }
                }
            })
        }
    }

    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): BaseViewModel

    abstract fun initView()

    open fun initData() {

    }

    open fun showLoading() {

    }

    open fun dismissLoading() {

    }

    open fun handleError() {

    }
}