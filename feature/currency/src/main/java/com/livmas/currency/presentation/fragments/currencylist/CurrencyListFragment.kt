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
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CurrencyListFragment : Fragment() {
    private val viewModel: CurrencyListViewModel by viewModels()
    private lateinit var binding: FragmentCurrencyListBinding

    private val currenciesAdapter = CurrencyRecyclerAdapter(listOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    override fun onStart() {
        super.onStart()

        setupObservers()
        viewModel.startCurrencyRefreshScheduling()
    }

    override fun onStop() {
        super.onStop()

        viewModel.stopCurrencyRefreshScheduling()
        disableObservers()
    }

    private fun setupViews() {
        setupRecyclerView()
    }
    private fun setupRecyclerView() {
        binding.rvCurrencies.adapter = currenciesAdapter
        binding.rvCurrencies.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun setupObservers() {
        setupCurrencyObserver()
        setupIsLoadingObserver()
        setupExceptionObserver()
    }
    private fun setupCurrencyObserver() {
        viewModel.currencies.observe(viewLifecycleOwner) {
            currenciesAdapter.updateCurrencies(it)
            currenciesAdapter.notifyDataSetChanged()

            binding.tvExceptionDisplay.visibility = View.GONE
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
    private fun setupExceptionObserver() {
        resources.apply {
            viewModel.exception.observe(viewLifecycleOwner) { e ->
                binding.tvExceptionDisplay.visibility = View.VISIBLE
                binding.tvExceptionDisplay.text = when(e) {
                    is IOException -> getString(R.string.exception_io_message)
                    else -> getString(R.string.exception_default_message)
                }
                binding.pbCurrencyLoading.visibility = View.GONE
            }
        }
    }
    private fun disableObservers() {
        viewModel.currencies.removeObservers(viewLifecycleOwner)
        viewModel.isLoading.removeObservers(viewLifecycleOwner)
        viewModel.exception.removeObservers(viewLifecycleOwner)
    }

    private fun fillRefreshText(calendar: Calendar) {
        val refreshPattern = SimpleDateFormat(resources.getString(R.string.time_pattern), Locale.getDefault())
        val refreshString = refreshPattern.format(calendar.time)
        binding.tvLastRefresh.text = resources.getString(R.string.last_refresh_pattern, refreshString)
    }
}