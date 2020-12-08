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


class RegistrationViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _userId = MutableLiveData<Event<Int>>()
    val userId: LiveData<Event<Int>> = _userId

    private val _errorMsg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>> = _errorMsg

    fun register(userName: String, userSurname: String, password: Int, email: String) {
        viewModelScope.launch(ioDispatcher) {
            userUseCase.register(userName, userSurname, password, email).let {
                when (it) {
                    is Result.Success -> _userId.postValue(Event(it.data))
                    is Result.Error -> {
                        Log.e("RegistrationViewModel", it.throwable.stackTraceToString())
                        _errorMsg.postValue(Event(it.throwable.stackTraceToString()))
                    }
                }
            }
        }
    }
}