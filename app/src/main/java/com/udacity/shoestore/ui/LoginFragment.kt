package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.LoginViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController()
        val toWelcome = LoginFragmentDirections.navToWelcome()

        val tryNavigate = {
            if (viewModel.validateInputs()) {
                navController.navigate(toWelcome)
            }
        }

        binding.loginButton.setOnClickListener { tryNavigate() }
        binding.createAccountButton.setOnClickListener { tryNavigate() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}