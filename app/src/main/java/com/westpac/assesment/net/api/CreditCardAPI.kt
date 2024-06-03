package com.westpac.assesment.net.api

import com.westpac.assesment.model.CreditCard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CreditCardAPI {

    @GET("credit_cards")
    suspend fun getAllCreditCards(@Query("size") size: Int): Response<List<CreditCard>>
}