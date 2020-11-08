package com.nikego.skycapitals.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikego.skycapitals.data.LoadState
import com.nikego.skycapitals.data.LoginInfo
import com.nikego.skycapitals.repositories.base.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(
        private val loginRepository: LoginRepository,
        private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _loginState = MutableLiveData<LoadState>()
    val loginState: LiveData<LoadState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch(ioDispatcher) {
            _loginState.postValue(LoadState.Loading)
            _loginState.postValue(if (loginRepository.login(LoginInfo(email, password))) LoadState.Success else LoadState.Error)
        }
    }
}