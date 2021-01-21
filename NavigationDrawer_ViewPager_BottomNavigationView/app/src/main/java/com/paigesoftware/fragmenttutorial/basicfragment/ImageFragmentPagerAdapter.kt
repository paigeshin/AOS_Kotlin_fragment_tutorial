package com.paigesoftware.fragmenttutorial.basicfragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ImageFragmentPagerAdapter(fragmentManger: FragmentManager): FragmentPagerAdapter(fragmentManger, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> BasicFirstImageFragment.newInstance()
            1 -> BasicSecondImageFragment.newInstance()
            else -> BasicFirstImageFragment.newInstance()
        }
    }

}