package com.example.tictac.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.tictac.UserRepository
import com.example.tictac.UsersAdapter
import com.example.tictac.databinding.RecordsFragmentBinding

class RecordsFragment:  BindingFragment<RecordsFragmentBinding>(RecordsFragmentBinding::inflate)  {
    private val repository = UserRepository
    private val adapter = UsersAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            rvUsers.adapter = adapter
            adapter.submitList(repository.getUsers())
            btnBack.setOnClickListener {
                findNavController().navigate(RecordsFragmentDirections.actionRecordsFragmentToMainFragment())
            }
        }
    }
}