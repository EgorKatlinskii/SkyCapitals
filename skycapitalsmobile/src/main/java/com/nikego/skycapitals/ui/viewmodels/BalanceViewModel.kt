package com.nikego.skycapitals.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.domain.UserUseCase
import com.nikego.skycapitals.utils.Event
import com.nikego.skycapitals.vo.UserItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


class BalanceViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _user = MutableLiveData<UserItem>()
    val user: LiveData<UserItem> = _user

    private val _errorMsg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>> = _errorMsg

    fun setUser(userId: Int) {
        viewModelScope.launch(ioDispatcher) {
            userUseCase.getUserItemById(userId).let {
                when (it) {
                    is Result.Success -> _user.postValue(it.data)
                    is Result.Error -> _errorMsg.postValue(Event(it.throwable.stackTraceToString()))
                }
            }
        }
    }
}