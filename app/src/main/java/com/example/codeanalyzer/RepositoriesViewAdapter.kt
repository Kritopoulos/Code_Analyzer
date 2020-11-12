package com.example.codeanalyzer


import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.codeanalyzer.apiGithub.ApiService

import com.example.codeanalyzer.apiGithub.GitRepositories
import com.example.codeanalyzer.branch.GetBranches

import com.example.codeanalyzer.databinding.FragmentUserBinding


@Suppress("DEPRECATION")
class RepositoriesViewAdapter(
    private val context: Context,
    private val item: List<GitRepositories>,
    private val apiService: ApiService,
    private val binding : FragmentUserBinding,
    private val navController: NavController
) :
    RecyclerView.Adapter<RepositoriesViewAdapter.ViewHolder>() {

    private var mycontext:Context = context

    @RequiresApi(Build.VERSION_CODES.O)
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {

        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
            GetBranches(item[adapterPosition].getOwner().getUserName(), item[adapterPosition].getName() , mycontext, binding,navController)
        }
        return this
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(mycontext).inflate(R.layout.repositories_row, null)
        return ViewHolder(view).listen { position, type ->
            val item = item[position]
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.repositoryName.text = "Repository: ${item[position].getName()}"

        holder.owner.text = "Owner: ${item[position].getOwner().getUserName()}"
        if(item[position].getSecurity())
            holder.security.text = "private"
        else
            holder.security.text = "public"
    }

    override fun getItemCount() = item.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // var security: TextView
        var repositoryName: TextView = itemView.findViewById<View>(R.id.repositoryName) as TextView
        var owner: TextView = itemView.findViewById<View>(R.id.owner) as TextView
        var security: TextView = itemView.findViewById<View>(R.id.security) as TextView
        //var owner:TextView
    }


}


