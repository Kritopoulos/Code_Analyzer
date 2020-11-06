package com.example.codeanalyzer.user

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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

        val repositoryRecyclerView = binding.recycler

        viewModelFactory = UserViewModelFactory(
            UserFragmentArgs.fromBundle(arguments!!).repoName!!, this.context!!
        )

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
        binding.userViewModel = viewModel

        UpdateUserDetails()

        //adapter for git repositories
        viewModel.reposList.observe(viewLifecycleOwner, Observer { reposList ->
            reposList?.let { it ->
                val layoutManager = LinearLayoutManager(
                    this.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                repositoryRecyclerView.layoutManager = layoutManager

                viewModel.apiService.observe(viewLifecycleOwner, { apiService ->

                    repositoryRecyclerView.adapter =
                        RepositoriesViewAdapter(this.requireContext(), it, apiService, binding,findNavController())
                }
                )
                binding.repos.visibility = View.VISIBLE
            }
        })

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    fun UpdateUserDetails() {
        viewModel.gitUser.observe(viewLifecycleOwner, Observer { newUser ->
            newUser?.let {
                binding.usersName.text = "Repository: ${newUser.getUserName()}"
                binding.publicReposTxt.text = "Public repos = ${newUser.getPublicRepos().toString()}"

                binding.profileUrl.setOnClickListener {
                    newUser.getHtmlUrl().let {
                        var uri =
                            Uri.parse(it) // missing 'http://' will cause crashed
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                    }
                }

                Picasso.get()
                    .load(newUser.getAvatarUrl())
                    .into(binding.avatar)
            } ?: run {
                binding.usersName.text = "Wrong user name"
            }
        })
    }


}