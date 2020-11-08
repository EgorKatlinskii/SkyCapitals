package com.nikego.skycapitals.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class EntryPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragmentList: ArrayList<Fragment> = ArrayList()

    override fun getCount(): Int =
            fragmentList.size

    override fun getItem(position: Int): Fragment =
            fragmentList[position]

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }
}