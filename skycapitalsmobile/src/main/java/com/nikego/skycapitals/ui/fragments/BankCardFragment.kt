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
import com.nikego.skycapitals.databinding.FragmentCardBinding
import com.nikego.skycapitals.di.Injectable
import com.nikego.skycapitals.ui.listeners.BankCardListener
import com.nikego.skycapitals.ui.viewmodels.BankCardViewModel
import com.nikego.skycapitals.utils.BankCardTextWatcher
import javax.inject.Inject

class BankCardFragment : Fragment(), BankCardListener, Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentCardBinding

    private val bankCardViewModel: BankCardViewModel by viewModels {
        viewModelFactory
    }

    private val navArgs: BankCardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(inflater).apply {
            receiveCardNumber.addTextChangedListener(BankCardTextWatcher())
            listener = this@BankCardFragment
        }
        bankCardViewModel.setBankCard(navArgs.cardNumber)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bankCardViewModel.run {
            bankCard.observe(viewLifecycleOwner) {
                binding.bankCard = it
            }
            errorMsg.observe(viewLifecycleOwner) { event ->
                event?.getContentIfNotHandled()?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onSendTransactionClicked() {
        binding.run {
            bankCardViewModel.sendTransaction(
                navArgs.cardNumber,
                receiveCardNumber.text.toString().replace(" ", "").toLong(),
                sumEditText.text.toString().toInt()
            )
        }
    }
}