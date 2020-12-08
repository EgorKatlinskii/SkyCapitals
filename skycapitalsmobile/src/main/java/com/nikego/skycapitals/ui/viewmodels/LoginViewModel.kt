package com.nikego.skycapitals.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.domain.UserUseCase
import com.nikego.skycapitals.utils.Event
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _errorMsg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>> = _errorMsg

    private val _userId = MutableLiveData<Event<Int>>()
    val userId: LiveData<Event<Int>> = _userId

    fun login(email: String, password: Int) {
        viewModelScope.launch(ioDispatcher) {
            userUseCase.login(email, password).let {
                when (it) {
                    is Result.Success -> _userId.postValue(Event(it.data))
                    is Result.Error -> {
                        Log.e("LoginViewModel", it.throwable.stackTraceToString())
                        _errorMsg.postValue(Event(it.throwable.stackTraceToString()))
                    }
                }
            }
        }
    }
}