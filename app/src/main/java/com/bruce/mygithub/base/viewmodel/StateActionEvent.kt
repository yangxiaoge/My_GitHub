package com.bruce.mygithub.base.viewmodel

/**
 * <pre>
 *     author: Bruce_Yang
 *     email : yangjianan@seuic.com
 *     time  : 2021/02/26
 *     desc  : 定义网络请求状态
 * </pre>
 */
//密封类扩展性更好
sealed class StateActionEvent

//加载状态
object LoadState : StateActionEvent()
//成功状态
object SuccessState : StateActionEvent()
//错误状态
class ErrorState(val msg: String?) : StateActionEvent()
