package com.nikego.skycapitals.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikego.skycapitals.databinding.ItemScoreBinding
import com.nikego.skycapitals.vo.ScoreItem


class ScoreItemAdapter(private val scoreItemListener: ScoreItemListener) :
    ListAdapter<ScoreItem, ScoreItemAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(parent, scoreItemListener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(
        private val binding: ItemScoreBinding,
        scoreItemListener: ScoreItemListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.listener = scoreItemListener
        }

        fun bind(scoreItem: ScoreItem) {
            binding.run {
                score = scoreItem
                executePendingBindings()
            }
        }

        companion object {

            fun create(parent: ViewGroup, scoreItemListener: ScoreItemListener) =
                ViewHolder(
                    ItemScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    scoreItemListener
                )
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<ScoreItem>() {

        override fun areItemsTheSame(oldItem: ScoreItem, newItem: ScoreItem): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: ScoreItem, newItem: ScoreItem): Boolean =
            oldItem == newItem
    }
}