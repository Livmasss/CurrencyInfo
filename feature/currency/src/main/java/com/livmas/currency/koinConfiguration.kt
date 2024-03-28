package com.livmas.currency

import com.livmas.currency.presentation.models.CurrencyModel
import com.livmas.currency.presentation.view_adapters.CurrencyRecyclerAdapter
import org.koin.dsl.module

val currencyFeatureModule = module {
    single<List<CurrencyModel>> {
        val list = mutableListOf(
            CurrencyModel("RUB", 1f),
            CurrencyModel("USD", 92.2628f),
            CurrencyModel("EUR", 99.7057f)
        )

        list
    }

    single<CurrencyRecyclerAdapter> {
        CurrencyRecyclerAdapter(get())
    }

}