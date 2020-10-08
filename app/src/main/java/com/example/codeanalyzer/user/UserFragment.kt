package com.example.codeanalyzer.user

import android.os.Bundle
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codeanalyzer.R
import com.example.codeanalyzer.RecyclerViewAdapter
import com.example.codeanalyzer.databinding.FragmentUserBinding
import com.squareup.picasso.Picasso




class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)

        var recyclerView = binding.recycler
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.userViewModel = viewModel

        val args: UserFragmentArgs by navArgs()

        Log.d("kappa",args.repoName)

        viewModel.gitUser.observe(viewLifecycleOwner, Observer { newName ->
            Log.d("kappa",newName.getUserName())
            binding.usersName.text = "Repository: ${newName.getUserName()}"
            binding.profileUrl.text = "Github profile \n ${newName.gethtmlUrl()}"
            Linkify.addLinks(binding.profileUrl, Linkify.WEB_URLS)
            Picasso.get()
                .load(newName.getAvatarUrl())
                .into(binding.avatar)
        })

        //adapter for git repositories
        viewModel.reposList.observe(viewLifecycleOwner, Observer { newList ->
            val layoutManager = LinearLayoutManager(this.context)
            recyclerView.layoutManager = layoutManager
            val recyclerViewAdapter = newList?.let {
                RecyclerViewAdapter(this.requireContext(), newList)
            }
            recyclerView.adapter = recyclerViewAdapter
        })

        //back button pressed
        requireActivity().onBackPressedDispatcher.addCallback(this) {
           Log.d("kappa","back")
        }
        return binding.root
    }
}