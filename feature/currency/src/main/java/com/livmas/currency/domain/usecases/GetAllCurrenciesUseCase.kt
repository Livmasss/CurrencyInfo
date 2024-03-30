package com.livmas.currency.domain.usecases

import com.livmas.currency.domain.usecases.repositories.CurrencyRepository
import com.livmas.currency.presentation.models.CurrencyModel

internal class GetAllCurrenciesUseCase(private val repository: CurrencyRepository) {
    fun execute(): List<CurrencyModel> =
        repository.getAllCurrencies()
}