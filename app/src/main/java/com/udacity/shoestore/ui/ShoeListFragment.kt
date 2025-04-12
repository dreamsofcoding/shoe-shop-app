package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeStoreViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {

    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShoeStoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
    }

    private fun setupNavigation() {
        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.navToShoeDetail())
        }

        viewModel.shoes.observe(viewLifecycleOwner) { shoes ->
            binding.shoeListContainer.removeAllViews()

            for (shoe in shoes) {
                val shoeBinding = DataBindingUtil.inflate<com.udacity.shoestore.databinding.ItemShoeBinding>(
                    layoutInflater,
                    R.layout.item_shoe,
                    binding.shoeListContainer,
                    false
                )
                shoeBinding.shoe = shoe
                binding.shoeListContainer.addView(shoeBinding.root)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}