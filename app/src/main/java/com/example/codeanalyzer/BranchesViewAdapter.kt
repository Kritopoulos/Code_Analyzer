package com.example.codeanalyzer

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.codeanalyzer.api.Branches

class BranchesViewAdapter (private val context: Context, private val item: List<Branches>) :
    RecyclerView.Adapter<BranchesViewAdapter.ViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.recycle_view_branches_row, null)
        return ViewHolder(view).listen{position, type ->
            val item = item[position]
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.branchName.text = item[position].getBranchName()
//        holder.owner.text = "Owner: ${item[position].getOwner().getUserName()}"
//        if(item[position].getSecurity())
//            holder.security.text = "private"
//        else
//            holder.security.text = "public"

    }

    override fun getItemCount()= item.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // var security: TextView
        var branchName: TextView
        //var owner:TextView

        init {

            branchName = itemView.findViewById<View>(R.id.branchName) as TextView
            // security = itemView.findViewById<View>(R.id.fullname) as TextView
            // owner = itemView.findViewById(R.id.owner)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
            Log.d("kappa","tsatsatsa")
        }
        return this
    }}
