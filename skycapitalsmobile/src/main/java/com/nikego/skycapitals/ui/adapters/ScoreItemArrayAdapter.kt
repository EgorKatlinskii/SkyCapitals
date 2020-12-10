package com.nikego.skycapitals.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.nikego.skycapitals.databinding.ItemScoreBinding
import com.nikego.skycapitals.vo.ScoreItem


class ScoreItemArrayAdapter(context: Context, items: List<ScoreItem>) :
    ArrayAdapter<ScoreItem>(context, 0, items) {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemScoreBinding.inflate(inflater)
        getItem(position)?.let {
            binding.score = it
        }

        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemScoreBinding.inflate(inflater)
        getItem(position)?.let {
            binding.score = it
        }

        return binding.root
    }

    override fun isEnabled(position: Int): Boolean = true
}