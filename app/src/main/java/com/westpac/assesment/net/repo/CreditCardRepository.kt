package com.westpac.assesment.net.repo

import com.westpac.assesment.model.CreditCard
import com.westpac.assesment.net.api.CreditCardAPI
import com.westpac.assesment.net.response.ApiResponse
import com.westpac.assesment.util.Constants.ERROR_CODE_EXCEPTION
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class CreditCardRepository @Inject constructor(private val creditCardAPI: CreditCardAPI) {

    /**
     * Load a list of credit cards from API Service.
     *
     * @param size Number of items expected in the response. Max is 100.
     *
     * @return A flow of [ApiResponse] containing a list of [CreditCard] as data.
     */
    suspend fun getAllCreditCards(size: Int) = flow<ApiResponse<List<CreditCard>>> {
        emit(ApiResponse.loading())
        creditCardAPI.getAllCreditCards(size = size).let { response ->
            if (response.isSuccessful) {
                emit(ApiResponse.success(response.body()))
            } else {
                emit(ApiResponse.failure(response.message(), response.code()))
                // Here we can check the different response status codes using response.code() if need
            }
        }
    }.catch {
        emit(ApiResponse.failure(it.message.toString(), ERROR_CODE_EXCEPTION))
    }.flowOn(Dispatchers.IO)
}