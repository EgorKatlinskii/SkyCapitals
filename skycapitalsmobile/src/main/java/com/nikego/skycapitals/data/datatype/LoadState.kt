package com.nikego.skycapitals.data.datatype


sealed class LoadState {
    object Loading : LoadState()
    object Error : LoadState()
    object Success : LoadState()
}