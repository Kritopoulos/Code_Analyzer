package com.example.codeanalyzer.entryFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codeanalyzer.R
import com.example.codeanalyzer.databinding.FragmentEntryBinding

@Suppress("DEPRECATION")
class EntryFragment : Fragment() {

    private lateinit var binding: FragmentEntryBinding
    private lateinit var viewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_entry, container, false)

        binding.searchButton.setOnClickListener {
            if (binding.repositoryName.text.toString() != "")
                findNavController().navigate(EntryFragmentDirections.entryToUser(binding.repositoryName.text.toString()))
            else
                Toast.makeText(this.activity, "Give a name", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

}
