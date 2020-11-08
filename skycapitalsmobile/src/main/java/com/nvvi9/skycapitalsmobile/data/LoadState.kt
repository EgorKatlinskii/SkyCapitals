package com.nvvi9.skycapitalsmobile.data


sealed class LoadState {
    object Loading : LoadState()
    object Error : LoadState()
    object Success : LoadState()
}