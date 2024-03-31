package com.livmas.data.currency

import com.livmas.data.currency.models.CurrencyGetResponse
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyRemoteAPI {
    @GET("/daily_json.js")
    fun getCurrencies(): Call<CurrencyGetResponse>
}