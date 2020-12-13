package com.nikego.skycapitals.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikego.skycapitals.databinding.ItemBankCardBinding
import com.nikego.skycapitals.ui.listeners.BankCardItemListener
import com.nikego.skycapitals.vo.BankCardItem


class BankCardItemAdapter(private val bankCardItemListener: BankCardItemListener) :
    ListAdapter<BankCardItem, BankCardItemAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(parent, bankCardItemListener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(
        private val binding: ItemBankCardBinding,
        bankCardItemListener: BankCardItemListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.listener = bankCardItemListener
        }

        fun bind(bankCardItem: BankCardItem) {
            binding.run {
                bankCard = bankCardItem
                executePendingBindings()
            }
        }

        companion object {

            fun create(parent: ViewGroup, bankCardItemListener: BankCardItemListener) =
                ViewHolder(
                    ItemBankCardBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    bankCardItemListener
                )
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<BankCardItem>() {

        override fun areItemsTheSame(oldItem: BankCardItem, newItem: BankCardItem): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: BankCardItem, newItem: BankCardItem): Boolean =
            oldItem == newItem
    }
}