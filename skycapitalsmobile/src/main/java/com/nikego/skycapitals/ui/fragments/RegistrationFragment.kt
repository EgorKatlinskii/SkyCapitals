package com.nikego.skycapitals.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.nikego.skycapitals.databinding.FragmentRegistrationBinding
import com.nikego.skycapitals.ui.viewmodels.RegistrationViewModel


class RegistrationFragment(viewModelFactory: ViewModelProvider.Factory) : Fragment() {

    private val registrationViewModel: RegistrationViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegistrationBinding.inflate(inflater).apply {
            btnRegister.setOnClickListener {
            }
        }

        return binding.root
    }
}