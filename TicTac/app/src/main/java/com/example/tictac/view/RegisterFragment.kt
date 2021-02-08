package com.example.tictac.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.tictac.UserRepository
import com.example.tictac.databinding.RegisterFragmentBinding


class RegisterFragment: BindingFragment<RegisterFragmentBinding>(RegisterFragmentBinding::inflate) {
    private val repository = UserRepository
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            btnGoPlay.setOnClickListener {
                if(etUser1.text.toString() == etUser2.text.toString()){
                    Toast.makeText(context, "Rewrite your name here can play 2 users", Toast.LENGTH_SHORT).show()
                }
                else {
                    repository.addStudent(etUser1.text.toString())
                    repository.addStudent(etUser2.text.toString())
                    context?.let { it1 -> hideKeyboardFrom(it1, it) }
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToGameFragment(etUser1.text.toString(),etUser2.text.toString()))
                }
             }
        }
    }
    private fun hideKeyboardFrom(
        context: Context,
        view: View
    ) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}