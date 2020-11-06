package com.example.codeanalyzer.analyzedFiles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.codeanalyzer.R
import com.example.codeanalyzer.databinding.FragmentAnalyzedFilesBinding
import com.example.codeanalyzer.user.UserViewModel


@Suppress("DEPRECATION")
class AnalyzedFilesFragment : Fragment() {

    private lateinit var binding : FragmentAnalyzedFilesBinding
    private lateinit var viewModel: AnalyzedFilesViewModel
    private lateinit var viewModelFactory: AnalyzedFilesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        AnalyzedFilesFragmentArgs.fromBundle(arguments!!).url!!
        Log.d("kappa","iuiuu"+AnalyzedFilesFragmentArgs.fromBundle(arguments!!).url!!)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_analyzed_files, container, false)

        viewModelFactory = AnalyzedFilesViewModelFactory(
            this.context!!, AnalyzedFilesFragmentArgs.fromBundle(arguments!!).url!!.toString())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AnalyzedFilesViewModel::class.java)
        binding.analyzedFilesViewModel = viewModel

        return binding.root

    }

}