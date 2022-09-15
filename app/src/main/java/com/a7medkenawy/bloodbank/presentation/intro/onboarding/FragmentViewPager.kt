package com.a7medkenawy.bloodbank.presentation.intro.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a7medkenawy.bloodbank.R
import com.a7medkenawy.bloodbank.databinding.FragmentViewPagerBinding
import com.a7medkenawy.bloodbank.presentation.intro.onboarding.adapter.FragmentPagerAdapter
import com.a7medkenawy.bloodbank.presentation.intro.onboarding.screens.FirstScreen
import com.a7medkenawy.bloodbank.presentation.intro.onboarding.screens.SecondScreen
import com.a7medkenawy.bloodbank.presentation.intro.onboarding.screens.ThirdScreen

class FragmentViewPager : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        val fragments = listOf(FirstScreen(), SecondScreen(), ThirdScreen())

        val pagerAdapter =
            FragmentPagerAdapter(fragments,
                requireActivity().supportFragmentManager,
                lifecycle)
        binding.viewPager.adapter = pagerAdapter
        return binding.root
    }
}