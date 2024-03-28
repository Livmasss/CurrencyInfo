package com.livmas.currency.presentation.view_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.livmas.currency.databinding.CurrencyItemLayoutBinding
import com.livmas.currency.presentation.models.CurrencyModel
import java.util.ArrayList
import java.util.LinkedList

class CurrencyRecyclerAdapter(private val data: MutableList<CurrencyModel>): RecyclerView.Adapter<CurrencyRecyclerAdapter.CurrencyHolder>() {
    inner class CurrencyHolder(private val binding: CurrencyItemLayoutBinding) : ViewHolder(binding.root) {
        fun bindData(model: CurrencyModel) {
            binding.tvCurrTitle.text = model.title
            binding.tvCurrValue.text = model.value.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val holderBinding = CurrencyItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyHolder(holderBinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val item = data[position]
        holder.bindData(item)
    }
}