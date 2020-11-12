package com.example.codeanalyzer.userRepositories.repositoriesBranches

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.codeanalyzer.R
import com.example.codeanalyzer.userRepositories.UserFragmentDirections

@Suppress("DEPRECATION")
class BranchesViewAdapter(private val context: Context, private val item: List<Branches>, private val navController: NavController, private val url: String) :
    RecyclerView.Adapter<BranchesViewAdapter.ViewHolder>() {

    @SuppressLint("InflateParams")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.row_branches, null)
        return ViewHolder(view).listen { position, type ->
            val item = item[position]
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.branchName.text = item[position].getBranchName()
    }

    override fun getItemCount() = item.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var branchName: TextView = itemView.findViewById<View>(R.id.branchName) as TextView
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
            item[position].getBranchName()
            navController.navigate(UserFragmentDirections.userToAnalyzedFiles(url+item[position].getBranchName()))
        }
        return this
    }
}
