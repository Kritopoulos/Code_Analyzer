package com.example.codeanalyzer.entryFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.codeanalyzer.R
import com.example.codeanalyzer.databinding.EntryFragmentBinding

@Suppress("DEPRECATION")
class EntryFragment : Fragment() {

    private lateinit var binding: EntryFragmentBinding
    private lateinit var viewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding = DataBindingUtil.inflate(inflater, R.layout.entry_fragment, container, false)

        binding.searchButton.setOnClickListener {
            if (binding.repositoryName.text.toString() != ""){
            val action = EntryFragmentDirections.entryToUser(binding.repositoryName.text.toString())
                NavHostFragment.findNavController(this).navigate(action)
            } else
                Toast.makeText(this.activity, "Give a name", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

}
