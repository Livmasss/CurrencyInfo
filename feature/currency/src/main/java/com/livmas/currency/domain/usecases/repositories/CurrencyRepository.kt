package com.livmas.currency.domain.usecases.repositories

import com.livmas.currency.presentation.models.CurrencyModel

interface CurrencyRepository {
    fun getAllCurrencies(): List<CurrencyModel>
}