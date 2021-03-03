package com.bruce.mygithub.home.viewmodel

import androidx.lifecycle.LiveData
import com.bruce.mygithub.base.viewmodel.BaseViewModel
import com.bruce.mygithub.home.model.ReleaseModel
import com.bruce.mygithub.user.repository.UserRepository

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/03/02
 *     desc  : HomeViewModel
 * </pre>
 */
class HomeViewModel(private val mUserRepository: UserRepository) : BaseViewModel() {
    //删除用户
    fun deleteUser() {
        launch {
            mUserRepository.deleteLocalUser(mUserRepository.getLocalUsers()[0])
        }
    }
    //获取release信息（github release）
    fun getReleases(): LiveData<ReleaseModel> = emit {
        mUserRepository.getReleases()[0]
    }
}