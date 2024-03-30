package com.livmas.data.repositories

import com.livmas.currency.domain.usecases.repositories.CurrencyRepository
import com.livmas.currency.presentation.models.CurrencyModel
import com.livmas.data.datasources.CurrencyRemoteDataSource

class CurrencyRepositoryImpl(remoteDataSource: CurrencyRemoteDataSource): CurrencyRepository {
    override fun getAllCurrencies(): List<CurrencyModel> {
        return mutableListOf(
            CurrencyModel("RUB", 1f),
            CurrencyModel("USD", 92.2628f),
            CurrencyModel("EUR", 99.7057f)
        )
    }
}