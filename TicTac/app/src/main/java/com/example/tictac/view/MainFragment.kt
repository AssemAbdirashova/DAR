package com.example.tictac.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tictac.databinding.MainFragmentBinding

class MainFragment: BindingFragment<MainFragmentBinding>(MainFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
           btnRecords.setOnClickListener {
               findNavController().navigate(MainFragmentDirections.actionMainFragmentToRecordsFragment())
           }
            btnPlay.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToRegisterFragment())
            }
        }
    }
}