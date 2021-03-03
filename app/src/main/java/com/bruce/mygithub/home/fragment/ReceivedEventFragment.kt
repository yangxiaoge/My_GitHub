package com.bruce.mygithub.home.fragment

import androidx.paging.PagedListAdapter
import com.bruce.mygithub.base.fragment.BasePagingVMFragment
import com.bruce.mygithub.base.viewmodel.BaseViewModel
import com.bruce.mygithub.constant.Settings
import com.bruce.mygithub.home.adapter.HomeAdapter
import com.bruce.mygithub.home.model.ReceivedEventModel
import com.bruce.mygithub.home.viewmodel.ReceivedEventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/03/02
 *     desc  : ReceivedEventFragment
 * </pre>
 */
class ReceivedEventFragment :
    BasePagingVMFragment<ReceivedEventModel, ReceivedEventViewModel, HomeAdapter.ViewHolder>() {

    private val mReceivedEventViewModel: ReceivedEventViewModel by viewModel()

    override fun getViewModel(): BaseViewModel = mReceivedEventViewModel

    override fun afterViewCreated() {
        mViewModel.user = Settings.Account.loginUser
    }

    override fun getAdapter(): PagedListAdapter<ReceivedEventModel, HomeAdapter.ViewHolder> =
        HomeAdapter(mActivity)
}