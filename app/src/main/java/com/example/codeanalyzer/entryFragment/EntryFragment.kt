package com.example.codeanalyzer.entryFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.codeanalyzer.R
import com.example.codeanalyzer.databinding.FragmentEntryBinding
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

class EntryFragment : Fragment() {

    private lateinit var binding: FragmentEntryBinding
    private lateinit var viewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_entry, container, false)

        viewModel = ViewModelProviders.of(this).get(EntryViewModel::class.java)
        binding.entryFragment = viewModel
        binding.lifecycleOwner = this

        binding.searchButton.setOnClickListener {
            val action = EntryFragmentDirections.entryToUser(binding.repositoryName.text.toString())
            findNavController().navigate(action)
        }
        return binding.root
    }

}
