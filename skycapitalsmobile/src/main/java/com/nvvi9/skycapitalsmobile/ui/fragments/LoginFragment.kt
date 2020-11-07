package com.nvvi9.skycapitalsmobile.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.nvvi9.skycapitalsmobile.databinding.FragmentLoginBinding
import com.nvvi9.skycapitalsmobile.di.Injectable
import com.nvvi9.skycapitalsmobile.ui.viewmodels.LoginViewModel
import javax.inject.Inject


class LoginFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val loginViewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }
}