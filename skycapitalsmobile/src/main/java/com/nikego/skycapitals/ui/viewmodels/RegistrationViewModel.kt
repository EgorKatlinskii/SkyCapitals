package com.nikego.skycapitals.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class RegistrationViewModel @Inject constructor(
        private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

}