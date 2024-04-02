package com.livmas.data.currency.repositories

import com.livmas.currency.domain.usecases.repositories.CurrencyRepository
import com.livmas.currency.presentation.models.CurrencyModel
import com.livmas.data.currency.datasources.CurrencyRemoteDataSource

internal class CurrencyRepositoryImpl(private val remoteDataSource: CurrencyRemoteDataSource): CurrencyRepository {
    override fun getAllCurrencies(): List<CurrencyModel> {

        return remoteDataSource.fetchCurrenciesData().valute.map {
            CurrencyModel(it.key, it.value.value)
        }
    }
}