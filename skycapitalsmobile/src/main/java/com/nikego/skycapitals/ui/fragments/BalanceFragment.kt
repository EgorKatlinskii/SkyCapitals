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
import com.nikego.skycapitals.databinding.FragmentBalanceBinding
import com.nikego.skycapitals.di.Injectable
import com.nikego.skycapitals.ui.viewmodels.BalanceViewModel
import javax.inject.Inject


class BalanceFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentBalanceBinding

    private val balanceViewModel: BalanceViewModel by viewModels {
        viewModelFactory
    }

    private val navArgs: BalanceFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBalanceBinding.inflate(inflater)
        balanceViewModel.setUser(navArgs.userId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        balanceViewModel.run {
            user.observe(viewLifecycleOwner) {
                binding.user = it
            }
            errorMsg.observe(viewLifecycleOwner) { event ->
                event?.getContentIfNotHandled()?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}