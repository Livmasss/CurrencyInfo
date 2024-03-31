package com.livmas.data.currency.datasources

import com.livmas.data.currency.CurrencyRemoteAPI
import com.livmas.data.currency.models.CurrencyGetResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class CurrencyRemoteDataSource {
    val BASE_URL = "https://www.cbr-xml-daily.ru"
    fun fetchCurrenciesData(): CurrencyGetResponse {
        if (api.getCurrencies().execute().body() == null)
            throw IOException("Fetching failed!")
        return api.getCurrencies().execute().body()!!
    }

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyRemoteAPI::class.java)
}