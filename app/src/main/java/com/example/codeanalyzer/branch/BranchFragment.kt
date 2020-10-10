package com.example.codeanalyzer.branch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.codeanalyzer.R
import com.example.codeanalyzer.databinding.FragmentBranchBinding

@Suppress("DEPRECATION")
class BranchFragment: Fragment() {


    private lateinit var binding: FragmentBranchBinding
    private lateinit var viewModel: BranchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_branch, container, false)
        viewModel = ViewModelProviders.of(this).get(BranchViewModel::class.java)

        return binding.root
    }
}