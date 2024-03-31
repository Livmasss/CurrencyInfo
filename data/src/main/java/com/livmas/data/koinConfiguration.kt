package com.livmas.data

import com.livmas.currency.domain.usecases.repositories.CurrencyRepository
import com.livmas.data.currency.datasources.CurrencyRemoteDataSource
import com.livmas.data.currency.repositories.CurrencyRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single {
        CurrencyRemoteDataSource()
    }
    single<CurrencyRepository> {
        CurrencyRepositoryImpl(get())
    }
}