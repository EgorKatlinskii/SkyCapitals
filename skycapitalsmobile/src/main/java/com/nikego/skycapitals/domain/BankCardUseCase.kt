package com.nikego.skycapitals.domain

import com.nikego.skycapitals.data.datatype.Result
import com.nikego.skycapitals.domain.mappers.UserItemMapper
import com.nikego.skycapitals.repositories.base.UserRepository
import javax.inject.Inject


class BankCardUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getBankCardByCardNumber(cardNumber: Long) =
        userRepository.getUserByCardNumber(cardNumber).let {
            when (it) {
                is Result.Success -> UserItemMapper.map(it.data).scores.map { it.bankCards }
                    .flatten().find { it.cardNumber == cardNumber }?.let {
                        Result.Success(it)
                    } ?: Result.Error(IllegalStateException("no card $cardNumber"))
                is Result.Error -> it
            }
        }

    suspend fun sendTransaction(cardNumber: Long, receiveCardNumber: Long, sum: Int) =
        userRepository.sendTransaction(cardNumber, receiveCardNumber, sum).let {
            when (it) {
                is Result.Success -> UserItemMapper.map(it.data).scores.map { it.bankCards }
                    .flatten().find { it.cardNumber == cardNumber }?.let {
                        Result.Success(it)
                    } ?: Result.Error(IllegalStateException("no card $cardNumber"))
                is Result.Error -> it
            }
        }
}