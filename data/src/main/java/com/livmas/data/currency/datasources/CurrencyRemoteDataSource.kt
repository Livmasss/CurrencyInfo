package com.livmas.data.currency.datasources

import com.livmas.data.BuildConfig
import com.livmas.data.currency.CurrencyRemoteAPI
import com.livmas.data.currency.models.CurrencyGetResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class CurrencyRemoteDataSource {
    fun fetchCurrenciesData(): CurrencyGetResponse {
        val response = api.getCurrencies().execute()
        if (response.body() == null)
            throw IOException("Fetching failed!")
        return response.body()!!
    }

    private val api = setupRetrofit()

    private fun setupRetrofit(): CurrencyRemoteAPI {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CurrencyRemoteAPI::class.java)
    }
}