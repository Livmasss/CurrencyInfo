package com.livmas.currency.presentation.fragments.currencylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.livmas.currency.R
import com.livmas.currency.databinding.FragmentCurrencyListBinding
import com.livmas.currency.presentation.view_adapters.CurrencyRecyclerAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CurrencyListFragment : Fragment() {
    private val viewModel: CurrencyListViewModel by viewModels()
    private lateinit var binding: FragmentCurrencyListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupObservers()
    }

    override fun onStart() {
        super.onStart()
        viewModel.startCurrencyScheduling()
    }
    override fun onStop() {
        super.onStop()
        viewModel.disableTimer()
    }

    private fun setupObservers() {
        setupCurrencyObserver()
        setupIsLoadingObserver()
    }
    private fun setupCurrencyObserver() {
        viewModel.currencies.observe(viewLifecycleOwner) {
            binding.rvCurrencies.adapter = CurrencyRecyclerAdapter(it)

            binding.rvCurrencies.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            fillRefreshText(Calendar.getInstance())
            viewModel.isLoading.postValue(false)
        }
    }
    private fun setupIsLoadingObserver() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.pbCurrencyLoading.visibility = if (it)
                View.VISIBLE
            else
                View.GONE
        }
    }
    private fun fillRefreshText(calendar: Calendar) {
        val refreshPattern = SimpleDateFormat(resources.getString(R.string.time_pattern), Locale.getDefault())
        val refreshString = refreshPattern.format(calendar.time)
        binding.tvLastRefresh.text = resources.getString(R.string.last_refresh_pattern, refreshString)
    }
}