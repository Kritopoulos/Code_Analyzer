package com.example.codeanalyzer.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codeanalyzer.BranchesViewAdapter
import com.example.codeanalyzer.R
import com.example.codeanalyzer.RepositoriesViewAdapter
import com.example.codeanalyzer.databinding.FragmentUserBinding
import com.squareup.picasso.Picasso


@Suppress("DEPRECATION")
class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var viewModelFactory: UserViewModelFactory

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)

        var recyclerView = binding.recycler
        viewModelFactory = UserViewModelFactory(UserFragmentArgs.fromBundle(arguments!!).repoName!!,this.context!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
        binding.userViewModel = viewModel


        viewModel.gitUser.observe(viewLifecycleOwner, Observer { newUser ->

            newUser?.let {
                binding.usersName.text = "Repository: ${newUser.getUserName()}"
                binding.profileUrl.text = "Github profile \n ${newUser.gethtmlUrl()}"
                Picasso.get()
                    .load(newUser.getAvatarUrl())
                    .into(binding.avatar)
            } ?: run {
                binding.usersName.text = "Wrong user name"
            }
        })

        //adapter for git repositories
       viewModel.reposList.observe(viewLifecycleOwner, Observer { newList ->
           newList?.let {
               val layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
               recyclerView.layoutManager = layoutManager
               recyclerView.adapter =
                   RepositoriesViewAdapter(this.requireContext(), newList, findNavController())
               binding.repos.text = "Git Hub Repository"
           }
       })

        viewModel.branchList.observe(viewLifecycleOwner, Observer { newList ->
            newList?.let {
                val branchLayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
                recyclerView.layoutManager = branchLayoutManager
                recyclerView.adapter =
                    BranchesViewAdapter(this.requireContext(), newList)
            }
        })
        //back button pressed
//        requireActivity().onBackPressedDispatcher.addCallback(this) {
//            Log.d("kappa", "back")
//        }
        return binding.root
    }
}