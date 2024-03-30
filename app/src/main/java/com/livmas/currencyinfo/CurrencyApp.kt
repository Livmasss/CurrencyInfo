package com.livmas.currencyinfo

import android.app.Application
import com.livmas.currency.currencyFeatureModule
import com.livmas.data.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CurrencyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)
            modules(currencyFeatureModule, dataModule)
        }
    }
}