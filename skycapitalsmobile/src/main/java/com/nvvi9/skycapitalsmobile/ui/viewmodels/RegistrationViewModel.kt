package com.nvvi9.skycapitalsmobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.nvvi9.skycapitalsmobile.repositories.base.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class RegistrationViewModel @Inject constructor(
        private val loginRepository: LoginRepository,
        private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


}