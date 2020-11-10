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
import java.util.*
import javax.inject.Inject


class RegistrationViewModel @Inject constructor(
        private val loginRepository: LoginRepository,
        private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _loadState = MutableLiveData<LoadState>()
    val loadState: LiveData<LoadState> = _loadState

    fun register(email: String, firstName: String, lastName: String, password: String) {
        viewModelScope.launch(ioDispatcher) {
            _loadState.postValue(LoadState.Loading)
            if (loginRepository.addUser(LoginInfo(UUID.randomUUID().toString(), email, firstName, lastName, password))) {
                _loadState.postValue(LoadState.Success)
            } else {
                _loadState.postValue(LoadState.Error)
            }
        }
    }
}