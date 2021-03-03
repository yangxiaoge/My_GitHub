package com.bruce.mygithub.user.repository

import com.bruce.mygithub.constant.Configs
import com.bruce.mygithub.home.model.ReceivedEventModel
import com.bruce.mygithub.home.model.ReleaseModel
import com.bruce.mygithub.repos.model.ReposItemModel
import com.bruce.mygithub.user.api.UserApi
import com.bruce.mygithub.user.dao.UserDao
import com.bruce.mygithub.user.model.AuthorizationReqModel
import com.bruce.mygithub.user.model.UserInfoModel
import com.bruce.mygithub.user.model.UserListModel
import com.bruce.mygithub.user.model.db.User

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/26
 *     desc  : UserRepository
 * </pre>
 */
class UserRepository(private val mUserApi: UserApi, private val mUserDao: UserDao) {
    suspend fun createOrGetAuthorization(
        authorizationReqModel: AuthorizationReqModel,
        client_id: String, fingerprint: String
    ) = mUserApi.createOrGetAuthorization(authorizationReqModel, client_id, fingerprint)

    suspend fun getAccessToken(url: String) = mUserApi.getAccessToken(url)

    suspend fun getUser() = mUserApi.getUser()

    suspend fun searchUsers(query: String, sort: String, order: String, page: Int): UserListModel =
        mUserApi.searchUsers(query, sort, order, page)

    suspend fun getUserInfo(user: String): UserInfoModel =
        mUserApi.getUserInfo(user)

    suspend fun getUserPublicRepos(user: String, page: Int): List<ReposItemModel> =
        mUserApi.getUserPublicRepos(user, page)

    suspend fun getStarredRepos(user: String, page: Int): List<ReposItemModel> =
        mUserApi.getStarredRepos(user, page)

    suspend fun queryReceivedEvents(user: String, page: Int): List<ReceivedEventModel> =
        mUserApi.queryReceivedEvents(user, page)

    suspend fun getReleases(): List<ReleaseModel> =
        mUserApi.getReleases(Configs.OWNER, Configs.OWNER_REPO, 1)

    suspend fun saveLocalUser(user: User) = mUserDao.insertAll(user)

    suspend fun getLocalUsers(): List<User> = mUserDao.getAll()

    suspend fun deleteLocalUser(user: User) = mUserDao.deleteAll(user)

}