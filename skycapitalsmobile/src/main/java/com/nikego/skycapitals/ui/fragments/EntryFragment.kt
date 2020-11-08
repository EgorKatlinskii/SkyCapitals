package com.nikego.skycapitals.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikego.skycapitals.databinding.FragmentEntryBinding


class EntryFragment : Fragment() {

    private lateinit var binding: FragmentEntryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEntryBinding.inflate(inflater)
        return binding.root
    }
}