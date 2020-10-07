package com.example.codeanalyzer.controler


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.codeanalyzer.R
import com.example.codeanalyzer.api.GitRepositories


class RecyclerViewAdapter(context: Context, item: List<GitRepositories>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val item: List<GitRepositories>
    private val context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("autolog", "onCreateViewHolder")
        val view: View = LayoutInflater.from(context).inflate(R.layout.recycle_view_row, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("autolog", "onBindViewHolder")
        holder.fullName.text = item[position].getFullName()
        holder.repositoryName.text = item[position].getName()
    }

    override fun getItemCount(): Int {
        Log.i("autolog", "getItemCount")
        return item.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fullName: TextView
        var repositoryName: TextView

        init {
            Log.i("autolog", "ViewHolder")
            repositoryName = itemView.findViewById<View>(R.id.repositoryName) as TextView
            fullName = itemView.findViewById<View>(R.id.fullName) as TextView
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });
        }
    }

    init {
        Log.i("autolog", "RecyclerViewAdapter")
        this.item = item
        this.context = context
    }
}