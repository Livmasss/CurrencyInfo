package com.livmas.currency.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.livmas.currency.databinding.FragmentCurrencyListBinding
import com.livmas.currency.presentation.view_adapters.CurrencyRecyclerAdapter
import org.koin.android.ext.android.inject

class CurrencyListFragment : Fragment() {

    private val recyclerAdapter: CurrencyRecyclerAdapter by inject()
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

        binding.rvCurrencies.adapter = recyclerAdapter
        binding.rvCurrencies.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}