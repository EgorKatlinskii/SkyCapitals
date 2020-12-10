package com.nikego.skycapitals.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.nikego.skycapitals.databinding.ItemBankCardBinding
import com.nikego.skycapitals.vo.BankCardItem


class BankCardItemArrayAdapter(context: Context, items: List<BankCardItem>) :
    ArrayAdapter<BankCardItem>(context, 0, items) {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemBankCardBinding.inflate(inflater)
        getItem(position)?.let {
            binding.bankCard = it
        }

        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemBankCardBinding.inflate(inflater)
        getItem(position)?.let {
            binding.bankCard = it
        }

        return binding.root
    }

    override fun isEnabled(position: Int): Boolean = true
}