package com.livmas.data

import com.livmas.currency.domain.usecases.repositories.CurrencyRepository
import com.livmas.data.datasources.CurrencyRemoteDataSource
import com.livmas.data.repositories.CurrencyRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single {
        CurrencyRemoteDataSource()
    }
    single<CurrencyRepository> {
        CurrencyRepositoryImpl(get())
    }
}