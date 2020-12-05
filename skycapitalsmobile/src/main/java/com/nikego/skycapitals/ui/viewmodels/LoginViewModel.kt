package com.nikego.skycapitals.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikego.skycapitals.data.User
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.data.skycapitalsserver.UserLogin
import com.nikego.skycapitals.repositories.base.UserRepository
import com.nikego.skycapitals.utils.Event
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _errorMsg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>> = _errorMsg

    private val _accessGiven = MutableLiveData<Event<User>>()
    val accessGiven: LiveData<Event<User>> = _accessGiven

    fun login(email: String, password: String) {
        viewModelScope.launch(ioDispatcher) {
            userRepository.login(UserLogin(email, password)).let {
                when (it) {
                    is Result.Success -> _accessGiven.postValue(Event(it.data))
                    is Result.Error -> {
                        _errorMsg.postValue(Event(it.throwable.stackTraceToString()))
                    }
                }
            }
        }
    }
}