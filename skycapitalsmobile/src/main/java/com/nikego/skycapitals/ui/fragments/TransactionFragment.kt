package com.nikego.skycapitals.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.nikego.skycapitals.databinding.FragmentTransactionBinding
import com.nikego.skycapitals.di.Injectable
import com.nikego.skycapitals.ui.adapters.BankCardItemArrayAdapter
import com.nikego.skycapitals.ui.adapters.ScoreItemArrayAdapter
import com.nikego.skycapitals.ui.viewmodels.TransactionViewModel
import javax.inject.Inject


class TransactionFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentTransactionBinding

    private val transactionViewModel: TransactionViewModel by viewModels {
        viewModelFactory
    }

    private val navArgs: TransactionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionBinding.inflate(inflater)

        transactionViewModel.setScores(navArgs.userId)

        navArgs.scoreNumber.takeIf { it != 0 }?.let {
            transactionViewModel.setCards(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        transactionViewModel.run {
            scores.observe(viewLifecycleOwner) { scores ->
                binding.spinnerScore.adapter =
                    context?.let { ScoreItemArrayAdapter(it, scores) }
            }

            bankCards.observe(viewLifecycleOwner) { cards ->
                binding.spinnerBankCard.adapter =
                    context?.let { BankCardItemArrayAdapter(it, cards) }
            }

            errorMsg.observe(viewLifecycleOwner) { event ->
                event?.getContentIfNotHandled()?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}