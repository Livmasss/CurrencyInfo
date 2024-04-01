package com.livmas.currency.presentation.fragments.currencylist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.currency.domain.usecases.GetAllCurrenciesUseCase
import com.livmas.currency.presentation.models.CurrencyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import java.util.Timer
import kotlin.concurrent.schedule

class CurrencyListViewModel: ViewModel() {
    private val timer = Timer()
    private val getCurrenciesUseCase: GetAllCurrenciesUseCase by inject(GetAllCurrenciesUseCase::class.java)

    val currencies: MutableLiveData<List<CurrencyModel>> by lazy {
        MutableLiveData()
    }
    val isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    fun startCurrencyScheduling() {
        CoroutineScope(Dispatchers.IO).launch {
            timer.schedule(0L, 30000L) {
                isLoading.postValue(true)
                currencies.postValue(getCurrenciesUseCase.execute())
            }
        }
    }
    fun disableTimer() =
        timer.cancel()

}