package com.example.codeanalyzer.userRepositories

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codeanalyzer.R
import com.example.codeanalyzer.databinding.UserFragmentBinding
import com.example.codeanalyzer.userRepositories.repositoriesBranches.GetBranches
import com.squareup.picasso.Picasso


@Suppress("DEPRECATION")
class UserFragment : Fragment() {

    private lateinit var binding: UserFragmentBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var viewModelFactory: UserViewModelFactory
    private lateinit var repositoryRecyclerView:RecyclerView
    private lateinit var repositoryOwner:String
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.user_fragment, container, false)

        //repositoryRecyclerView = binding.recycler

        viewModelFactory = UserViewModelFactory(
            UserFragmentArgs.fromBundle(requireArguments()).repoName!!, this.requireContext()
        )

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)

        binding.userViewModel = viewModel

        binding.startBar.visibility = View.VISIBLE

        Handler().postDelayed({ updateUserDetails() }, 1000)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    fun updateUserDetails() {

        viewModel.gitUser.observe(viewLifecycleOwner, Observer { newUser ->
            newUser?.let {
                binding.profileUrl.text = this.context?.getString(R.string.git_profile)
                binding.startBar.visibility = View.GONE
                binding.usersName.text = "Repository: ${newUser.getUserName()}"
                repositoryOwner = newUser.getUserName()
                binding.publicReposTxt.text =
                    "Public repos = ${newUser.getPublicRepos().toString()}"

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
                //adapter for git repositories
                updateList()
            } ?: run {

                Toast.makeText(this.context, "Failed to initialize user", Toast.LENGTH_LONG).show()
                findNavController().navigate(UserFragmentDirections.actionUserDestinationToEntryDestination())
            }
        })
    }

    private fun updateList(){

        viewModel.reposList.observe(viewLifecycleOwner, Observer { reposList ->
            reposList?.let { it ->

                //var list: ArrayList<String> = it
                var list: ArrayList<String> = ArrayList()
                for( repo in it){
                    list.add(repo.getName())
                }

                val arrayAdapter: ArrayAdapter<String> =
                    ArrayAdapter<String>(requireContext(),
                        R.layout.spinner_checked_text,
                        list)

                arrayAdapter.setDropDownViewResource(R.layout.spinner_item)
                binding.spinnerRepos.adapter = arrayAdapter

                binding.spinnerRepos.onItemSelectedListener = object : OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long,
                    ) {

                        val tutorialsName: String = parent.getItemAtPosition(position).toString()

                        binding.loadingBranches.visibility = View.VISIBLE
                        binding.loadingBranches.bringToFront()
                        GetBranches(repositoryOwner, tutorialsName, requireContext(), binding,findNavController())
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }


//                val layoutManager = LinearLayoutManager(
//                    this.context,
//                    LinearLayoutManager.HORIZONTAL,
//                    false
//                )
//                repositoryRecyclerView.layoutManager = layoutManager
//
//                viewModel.apiService.observe(viewLifecycleOwner, { apiService ->
//                    repositoryRecyclerView.adapter =
//                        RepositoriesViewAdapter(
//                            this.requireContext(),
//                            it,
//                            apiService,
//                            binding,
//                            findNavController()
//                        )
//                })
                binding.repos.visibility = View.VISIBLE
            }
        })


    }


}