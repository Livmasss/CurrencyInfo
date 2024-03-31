package com.livmas.currency.presentation.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.currency.domain.usecases.GetAllCurrenciesUseCase
import com.livmas.currency.presentation.models.CurrencyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class CurrencyListViewModel: ViewModel() {
    init {
        println("Viewmodel Created")
        fillWithData()
    }
    private val getCurrenciesUseCase: GetAllCurrenciesUseCase by inject(GetAllCurrenciesUseCase::class.java)

    val currencies: MutableLiveData<List<CurrencyModel>> by lazy {
        MutableLiveData()
    }

    private fun fillWithData() {
        CoroutineScope(Dispatchers.IO).launch {
            currencies.postValue(getCurrenciesUseCase.execute())
        }
    }
}