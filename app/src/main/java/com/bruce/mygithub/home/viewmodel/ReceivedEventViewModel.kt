package com.bruce.mygithub.home.viewmodel

import com.bruce.mygithub.base.viewmodel.BaseLPagingViewModel
import com.bruce.mygithub.home.model.ReceivedEventModel
import com.bruce.mygithub.user.repository.UserRepository

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/03/02
 *     desc  : ReceivedEventViewModel
 * </pre>
 */
class ReceivedEventViewModel(private val mUserRepository: UserRepository) :
    BaseLPagingViewModel<ReceivedEventModel>() {

    var user = ""

    override suspend fun getDataList(page: Int): List<ReceivedEventModel> {
        return mUserRepository.queryReceivedEvents(user, page)
    }
}