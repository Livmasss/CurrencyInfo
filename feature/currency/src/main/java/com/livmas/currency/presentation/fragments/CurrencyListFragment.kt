package com.livmas.currency.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.livmas.currency.databinding.FragmentCurrencyListBinding
import com.livmas.currency.presentation.view_adapters.CurrencyRecyclerAdapter

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

        viewModel.currencies.observe(viewLifecycleOwner) {
            println("!!!")
            binding.rvCurrencies.adapter = CurrencyRecyclerAdapter(it)

            binding.rvCurrencies.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}