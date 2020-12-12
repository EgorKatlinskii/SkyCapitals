package com.nikego.skycapitals.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikego.skycapitals.databinding.FragmentBalanceBinding
import com.nikego.skycapitals.di.Injectable
import com.nikego.skycapitals.ui.adapters.ScoreItemAdapter
import com.nikego.skycapitals.ui.listeners.BalanceListener
import com.nikego.skycapitals.ui.listeners.ScoreItemListener
import com.nikego.skycapitals.ui.viewmodels.BalanceViewModel
import com.nikego.skycapitals.utils.SpringAddItemAnimator
import com.nikego.skycapitals.vo.ScoreItem
import javax.inject.Inject


class BalanceFragment : Fragment(), ScoreItemListener, BalanceListener, Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentBalanceBinding

    private val balanceViewModel: BalanceViewModel by viewModels {
        viewModelFactory
    }

    private val navArgs: BalanceFragmentArgs by navArgs()

    private val scoreItemAdapter = ScoreItemAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBalanceBinding.inflate(inflater).apply {
            listener = this@BalanceFragment
            itemScoreView.run {
                adapter = scoreItemAdapter
                itemAnimator = SpringAddItemAnimator()
                layoutManager = LinearLayoutManager(
                    requireNotNull(this@BalanceFragment.activity).application,
                    RecyclerView.VERTICAL,
                    false
                )
            }
        }
        balanceViewModel.setUser(navArgs.userId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        balanceViewModel.run {
            user.observe(viewLifecycleOwner) {
                binding.user = it
                scoreItemAdapter.submitList(it.scores)
                scoreItemAdapter.notifyDataSetChanged()
            }
            errorMsg.observe(viewLifecycleOwner) { event ->
                event?.getContentIfNotHandled()?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onClick(item: ScoreItem) {
        findNavController().navigate(
            BalanceFragmentDirections.actionBalanceFragmentToScoreFragment(
                item.scoreNumber
            )
        )
    }

    override fun onNewScoreClicked(userId: Int) {
        balanceViewModel.addScore(userId)
    }
}