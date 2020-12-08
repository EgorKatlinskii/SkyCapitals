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
import com.nikego.skycapitals.databinding.FragmentRegistrationBinding
import com.nikego.skycapitals.ui.viewmodels.RegistrationViewModel


class RegistrationFragment(viewModelFactory: ViewModelProvider.Factory) : Fragment() {

    private val registrationViewModel: RegistrationViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater).apply {
            btnRegister.setOnClickListener {
                registrationViewModel.register(
                    userName = firstNameEditText.text.toString(),
                    userSurname = lastNameEditText.text.toString(),
                    email = emailEditText.text.toString(),
                    password = passwordEditText.text.toString().toInt(),
                )
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        registrationViewModel.run {
            userId.observe(viewLifecycleOwner) { event ->
                event?.getContentIfNotHandled()?.let {
                    findNavController()
                        .navigate(AuthFragmentDirections.actionAuthFragmentToBalanceFragment(it))
                }
            }
            errorMsg.observe(viewLifecycleOwner) { event ->
                event?.getContentIfNotHandled()?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}