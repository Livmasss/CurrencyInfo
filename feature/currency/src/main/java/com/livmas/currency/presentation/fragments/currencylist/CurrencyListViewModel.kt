package com.livmas.currency.presentation.fragments.currencylist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.currency.BuildConfig
import com.livmas.currency.domain.usecases.GetAllCurrenciesUseCase
import com.livmas.currency.presentation.models.CurrencyModel
import org.koin.java.KoinJavaComponent.inject
import java.util.Timer
import kotlin.concurrent.schedule

class CurrencyListViewModel: ViewModel() {
    private lateinit var timer: Timer
    private val getCurrenciesUseCase: GetAllCurrenciesUseCase by inject(GetAllCurrenciesUseCase::class.java)

    val currencies: MutableLiveData<List<CurrencyModel>> by lazy {
        MutableLiveData()
    }
    val exception: MutableLiveData<Exception> by lazy {
        MutableLiveData()
    }
    val isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    fun startCurrencyRefreshScheduling() {
        timer = Timer()
        timer.schedule(0L, BuildConfig.CURRENCY_REFRESH_PERIOD) {
            try {
                isLoading.postValue(true)
                currencies.postValue(getCurrenciesUseCase.execute())
            }
            catch (e: java.lang.Exception) {
                exception.postValue(e)
            }
        }
    }
    fun stopCurrencyRefreshScheduling() =
        timer.cancel()
}