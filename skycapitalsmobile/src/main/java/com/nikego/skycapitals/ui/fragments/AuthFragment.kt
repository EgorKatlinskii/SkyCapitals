package com.nikego.skycapitals.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikego.skycapitals.databinding.FragmentAuthBinding
import com.nikego.skycapitals.di.Injectable
import com.nikego.skycapitals.ui.adapters.EntryPagerAdapter


class AuthFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAuthBinding.inflate(inflater).apply {
            viewPager.adapter = EntryPagerAdapter(parentFragmentManager).apply {
                addFragment(LoginFragment())
                addFragment(RegistrationFragment())
            }
        }
        return binding.root
    }
}