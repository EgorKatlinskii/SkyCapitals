package com.nikego.skycapitals.data


sealed class LoadState {
    object Loading : LoadState()
    object Error : LoadState()
    object Success : LoadState()
}