package com.nikego.skycapitals.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.domain.ScoreUseCase
import com.nikego.skycapitals.utils.Event
import com.nikego.skycapitals.vo.ScoreItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


class ScoreViewModel @Inject constructor(
    private val scoreUseCase: ScoreUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _score = MutableLiveData<ScoreItem>()
    val score: LiveData<ScoreItem> = _score

    private val _errorMsg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>> = _errorMsg

    fun setScore(scoreNumber: Int) {
        viewModelScope.launch(ioDispatcher) {
            scoreUseCase.getCardsByScoreNumber(scoreNumber).let {
                when (it) {
                    is Result.Success -> _score.postValue(it.data)
                    is Result.Error -> _errorMsg.postValue(Event(it.throwable.stackTraceToString()))
                }
            }
        }
    }
}