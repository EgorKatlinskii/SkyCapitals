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
import com.nikego.skycapitals.databinding.FragmentLoginBinding
import com.nikego.skycapitals.ui.viewmodels.LoginViewModel


class LoginFragment(viewModelFactory: ViewModelProvider.Factory) : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater).apply {
            btnLogin.setOnClickListener {
                loginViewModel.login(
                    loginEmailEditText.text.toString(),
                    loginPasswordEditText.text.toString().toInt()
                )
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginViewModel.run {
            errorMsg.observe(viewLifecycleOwner) {
                it?.getContentIfNotHandled()?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            userId.observe(viewLifecycleOwner) { event ->
                event?.getContentIfNotHandled()?.let {
                    findNavController()
                        .navigate(
                            AuthFragmentDirections.actionAuthFragmentToBalanceFragment2(it)
                        )
                }
            }
        }
    }
}