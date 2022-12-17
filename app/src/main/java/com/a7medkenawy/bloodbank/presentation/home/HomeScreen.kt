package com.a7medkenawy.bloodbank.presentation.home

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.a7medkenawy.bloodbank.R
import com.a7medkenawy.bloodbank.databinding.FragmentHomeScreenBinding

class HomeScreen : Fragment() {
    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.homeContainerView) as NavHostFragment
        navController = navHostFragment.navController
//        navController = Navigation.findNavController(binding.root, R.id.homeContainerView)
        setUpSmoothBottomBar()

        return binding.root
    }

    private fun setUpSmoothBottomBar() {
        val popupMenu = PopupMenu(requireContext(), null)
        popupMenu.inflate(R.menu.menu_bottom)
        val menu = popupMenu.menu
        binding.bottomBar.setupWithNavController(menu, navController)
    }
}