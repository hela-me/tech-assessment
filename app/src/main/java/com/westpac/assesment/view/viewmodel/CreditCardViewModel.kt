package com.westpac.assesment.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.westpac.assesment.model.CreditCard
import com.westpac.assesment.net.repo.CreditCardRepository
import com.westpac.assesment.net.response.ApiResponse
import com.westpac.assesment.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreditCardViewModel @Inject constructor(private val repository: CreditCardRepository) : ViewModel() {

    private val _creditCards = MutableStateFlow(listOf<CreditCard>())

    /**
     * Emits a list of [CreditCard] within a [StateFlow].
     */
    val creditCards = _creditCards.asStateFlow()

    private val _isLoadingData = MutableStateFlow(false)

    /**
     * Emits loading status as a [Boolean] within a [StateFlow].
     */
    val isLoadingData = _isLoadingData.asStateFlow()

    private val _apiFailure = MutableSharedFlow<String>()

    /**
     * Emits a loading failure message as a [String] within a [SharedFlow].
     */
    val apiFailure = _apiFailure.asSharedFlow()

    init {
        loadCreditCards(100) //Pagination should be applied when possible
    }

    /**
     * Emits a list of [CreditCard] from API Service.
     *
     * @param size Number of items expected in the response. Max is 100.
     */
    fun loadCreditCards(size: Int) = viewModelScope.launch {
        repository.getAllCreditCards(size).collectLatest { response ->
            when (response.status) {
                ApiResponse.ResponseStatus.SUCCESS -> {
                    _creditCards.emit(response.data ?: listOf())
                    _isLoadingData.emit(false)
                }

                ApiResponse.ResponseStatus.LOADING -> _isLoadingData.emit(true)
                ApiResponse.ResponseStatus.FAILURE -> {
                    //TODO: Use [response.errorCode] to differentiate error codes and emmit different messages according to the business logic.
                    _apiFailure.emit(response.message ?: Constants.API_ERROR_UNKNOWN)
                    _isLoadingData.emit(false)
                }
            }
        }
    }
}