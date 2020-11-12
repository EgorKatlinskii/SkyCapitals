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
import com.nikego.skycapitals.R
import com.nikego.skycapitals.data.datatype.LoadState
import com.nikego.skycapitals.databinding.FragmentLoginBinding
import com.nikego.skycapitals.ui.viewmodels.LoginViewModel


class LoginFragment(viewModelFactory: ViewModelProvider.Factory) : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater).apply {
            btnLogin.setOnClickListener {
                loginViewModel.login(loginEmail.text.toString(), loginPassword.text.toString())
            }
        }

        loginViewModel.loginState.observe(viewLifecycleOwner) {
            when (it) {
                is LoadState.Success -> findNavController().navigate(R.id.action_global_homeFragment)
                is LoadState.Error -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}