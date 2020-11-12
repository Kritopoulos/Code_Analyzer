package com.example.codeanalyzer.diallog


import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import com.example.codeanalyzer.R


class InfoDialog (var c: Activity) : Dialog(c), View.OnClickListener {

    var d: Dialog? = null
    var yes: Button? = null
    var no: Button? = null

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.res_dialog)

        yes = findViewById(R.id.btn_yes)
        no = findViewById(R.id.btn_no)

        yes?.setOnClickListener(this)
        no?.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_yes -> c.finish()
            R.id.btn_no -> dismiss()
            else -> {
            }
        }
        dismiss()
    }
}