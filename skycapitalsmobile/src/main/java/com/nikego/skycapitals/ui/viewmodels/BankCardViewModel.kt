package com.nikego.skycapitals.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.domain.BankCardUseCase
import com.nikego.skycapitals.utils.Event
import com.nikego.skycapitals.vo.BankCardItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


class BankCardViewModel @Inject constructor(
    private val bankCardUseCase: BankCardUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _bankCard = MutableLiveData<BankCardItem>()
    val bankCard: LiveData<BankCardItem> = _bankCard

    private val _errorMsg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>> = _errorMsg

    fun setBankCard(cardNumber: Long) {
        viewModelScope.launch(ioDispatcher) {
            bankCardUseCase.getBankCardByCardNumber(cardNumber).let {
                when (it) {
                    is Result.Success -> _bankCard.postValue(it.data)
                    is Result.Error -> _errorMsg.postValue(Event(it.throwable.stackTraceToString()))
                }
            }
        }
    }

    fun sendTransaction(cardNumber: Long, receiveCardNumber: Long, sum: Int) {
        viewModelScope.launch(ioDispatcher) {
            bankCardUseCase.sendTransaction(cardNumber, receiveCardNumber, sum).let {
                when (it) {
                    is Result.Success -> _bankCard.postValue(it.data)
                    is Result.Error -> _errorMsg.postValue(Event(it.throwable.stackTraceToString()))
                }
            }
        }
    }
}