package com.livmas.currencyinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.livmas.currency.presentation.fragments.CurrencyListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startCurrencyFragment()
    }

    private fun startCurrencyFragment() {
        val fragment = CurrencyListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fcvContainer, fragment)
            .commit()
    }
}