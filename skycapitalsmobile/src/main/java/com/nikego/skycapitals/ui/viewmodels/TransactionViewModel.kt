package com.nikego.skycapitals.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.domain.TransactionUseCase
import com.nikego.skycapitals.utils.Event
import com.nikego.skycapitals.vo.BankCardItem
import com.nikego.skycapitals.vo.ScoreItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


class TransactionViewModel @Inject constructor(
    private val transactionUseCase: TransactionUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _scores = MutableLiveData<List<ScoreItem>>()
    val scores: LiveData<List<ScoreItem>> = _scores

    private val _bankCards = MutableLiveData<List<BankCardItem>>()
    val bankCards: LiveData<List<BankCardItem>> = _bankCards

    private val _errorMsg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>> = _errorMsg

    fun setScores(userId: Int) {
        viewModelScope.launch(ioDispatcher) {
            transactionUseCase.getScoresByUserId(userId).let {
                when (it) {
                    is Result.Success -> _scores.postValue(it.data)
                    is Result.Error -> _errorMsg.postValue(Event(it.throwable.stackTraceToString()))
                }
            }
        }
    }

    fun setCards(scoreNumber: Int) {
        viewModelScope.launch(ioDispatcher) {
            transactionUseCase.getCardsByScoreNumber(scoreNumber).let {
                when (it) {
                    is Result.Success -> _bankCards.postValue(it.data)
                    is Result.Error -> _errorMsg.postValue(Event(it.throwable.stackTraceToString()))
                }
            }
        }
    }
}