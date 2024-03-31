package com.livmas.currency

import com.livmas.currency.domain.usecases.GetAllCurrenciesUseCase
import org.koin.dsl.module

val currencyFeatureModule = module {
    single<GetAllCurrenciesUseCase> {
        GetAllCurrenciesUseCase(get())
    }
}