package com.example.codeanalyzer


import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import com.example.codeanalyzer.api.GitRepositories
import com.example.codeanalyzer.api.UserDetails
import kotlinx.android.synthetic.main.fragment_user.view.*
import kotlinx.android.synthetic.main.recycle_view_row.view.*


@Suppress("DEPRECATION")
class RecyclerViewAdapter(private val context: Context, private val item: List<GitRepositories>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.recycle_view_row, null)
        return ViewHolder(view).listen{position, type ->
            val item = item[position]
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.fullName.text = item[position].getFullName()
        holder.repositoryName.text = item[position].getName()

    }

    override fun getItemCount()= item.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fullName: TextView
        var repositoryName: TextView

        init {

            repositoryName = itemView.findViewById<View>(R.id.repositoryName) as TextView
            fullName = itemView.findViewById<View>(R.id.fullName) as TextView
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {

        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
            Log.d("kappa",item[adapterPosition].getFullName())
        }
        return this
    }}


