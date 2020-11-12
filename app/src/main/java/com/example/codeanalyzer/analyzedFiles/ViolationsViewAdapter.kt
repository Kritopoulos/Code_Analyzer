package com.example.codeanalyzer.analyzedFiles

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.codeanalyzer.R
import com.example.codeanalyzer.analyzedFiles.analyzerIncome.Violation
import com.example.codeanalyzer.databinding.AnalyzedFilesFragmentBinding
import com.example.codeanalyzer.diallog.InfoDialog


@Suppress("DEPRECATION")
class ViolationsViewAdapter(
    private val context: Context,
    private val item: List<Violation>,
    private val binding: AnalyzedFilesFragmentBinding,
    private val activity:Activity
) :
    RecyclerView.Adapter<ViolationsViewAdapter.ViewHolder>() {
    private var mycontext: Context = context

    @RequiresApi(Build.VERSION_CODES.O)
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
            InfoDialog(activity).show()
        }

        return this
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(mycontext).inflate(R.layout.row_violations, null)

        return ViewHolder(view).listen { position, type ->
            val item = item[position]
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.description.text = "Description: ${item[position].getDescription()}"
        holder.beginLine.text = "Rule: ${item[position].getRule()}"


    }

    override fun getItemCount() = item.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // var security: TextView
        var description: TextView = itemView.findViewById<View>(R.id.descriptionTxt) as TextView
        var beginLine: TextView = itemView.findViewById<View>(R.id.rule) as TextView
        //var owner:TextView
    }


}