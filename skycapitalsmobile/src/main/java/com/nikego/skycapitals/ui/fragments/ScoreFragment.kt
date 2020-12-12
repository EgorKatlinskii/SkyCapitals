package com.nikego.skycapitals.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikego.skycapitals.data.CardType
import com.nikego.skycapitals.databinding.FragmentScoreBinding
import com.nikego.skycapitals.di.Injectable
import com.nikego.skycapitals.ui.adapters.BankCardItemAdapter
import com.nikego.skycapitals.ui.listeners.BankCardItemListener
import com.nikego.skycapitals.ui.listeners.ScoreListener
import com.nikego.skycapitals.ui.viewmodels.ScoreViewModel
import com.nikego.skycapitals.utils.SpringAddItemAnimator
import com.nikego.skycapitals.vo.BankCardItem
import java.util.*
import javax.inject.Inject


class ScoreFragment : Fragment(), BankCardItemListener, ScoreListener, Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentScoreBinding

    private val scoreViewModel: ScoreViewModel by viewModels {
        viewModelFactory
    }

    private val navArgs: ScoreFragmentArgs by navArgs()

    private val bankCardItemAdapter = BankCardItemAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScoreBinding.inflate(inflater).apply {
            listener = this@ScoreFragment
            itemBankCardView.run {
                adapter = bankCardItemAdapter
                itemAnimator = SpringAddItemAnimator()
                layoutManager = LinearLayoutManager(
                    requireNotNull(this@ScoreFragment.activity).application,
                    RecyclerView.VERTICAL,
                    false
                )
            }
        }

        scoreViewModel.setScore(navArgs.scoreNumber)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        scoreViewModel.run {
            score.observe(viewLifecycleOwner) {
                binding.score = it
                bankCardItemAdapter.submitList(it.bankCards)
                bankCardItemAdapter.notifyDataSetChanged()
            }
            errorMsg.observe(viewLifecycleOwner) { event ->
                event?.getContentIfNotHandled()?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onClick(bankCardItem: BankCardItem) {
        findNavController().navigate(
            ScoreFragmentDirections.actionScoreFragmentToBankCardFragment(
                bankCardItem.cardNumber
            )
        )
    }

    override fun onNewCardClicked(scoreNumber: Int) {
        val cardTypes = arrayOf("Visa", "MasterCard")
        var selectedType = 0
        AlertDialog.Builder(requireContext())
            .setTitle("Choose card type")
            .setSingleChoiceItems(cardTypes, 0) { _, i ->
                selectedType = i
                Toast.makeText(context, i.toString(), Toast.LENGTH_SHORT).show()
            }.setPositiveButton("OK") { _, _ ->
                scoreViewModel.addBankCard(
                    CardType.valueOf(cardTypes[selectedType].toUpperCase(Locale.ROOT)), scoreNumber
                )
            }.setNegativeButton("Cancel", null).show()
    }
}