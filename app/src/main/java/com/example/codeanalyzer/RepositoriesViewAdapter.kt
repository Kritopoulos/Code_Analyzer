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
import com.example.codeanalyzer.api.GitRepositories


@Suppress("DEPRECATION")
class RepositoriesViewAdapter(private val context: Context, private val item: List<GitRepositories>, private val navController: NavController) :
    RecyclerView.Adapter<RepositoriesViewAdapter.ViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.recycle_view_row, null)
        return ViewHolder(view).listen{position, type ->
            val item = item[position]
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.repositoryName.text = "Repository: ${item[position].getName()}"
//        holder.owner.text = "Owner: ${item[position].getOwner().getUserName()}"
//        if(item[position].getSecurity())
//            holder.security.text = "private"
//        else
//            holder.security.text = "public"

    }

    override fun getItemCount()= item.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       // var security: TextView
        var repositoryName: TextView
        //var owner:TextView

        init {

            repositoryName = itemView.findViewById<View>(R.id.repositoryName) as TextView
           // security = itemView.findViewById<View>(R.id.fullname) as TextView
           // owner = itemView.findViewById(R.id.owner)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
            navController.navigate(R.id.user_to_branch)
        }
        return this
    }}


