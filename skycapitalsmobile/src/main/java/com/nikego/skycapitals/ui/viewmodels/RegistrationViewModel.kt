package com.nikego.skycapitals.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.nikego.skycapitals.repositories.base.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class RegistrationViewModel @Inject constructor(
        private val loginRepository: LoginRepository,
        private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


}