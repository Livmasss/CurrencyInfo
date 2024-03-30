package com.livmas.currency

import com.livmas.currency.domain.usecases.GetAllCurrenciesUseCase
import com.livmas.currency.presentation.models.CurrencyModel
import com.livmas.currency.presentation.view_adapters.CurrencyRecyclerAdapter
import org.koin.dsl.module

val currencyFeatureModule = module {
    single<GetAllCurrenciesUseCase> {
        GetAllCurrenciesUseCase(get())
    }
    single<List<CurrencyModel>> {
        val useCase: GetAllCurrenciesUseCase by inject()
        useCase.execute()
    }

    single {
        CurrencyRecyclerAdapter(get())
    }
}