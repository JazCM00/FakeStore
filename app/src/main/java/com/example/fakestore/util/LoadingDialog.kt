package com.example.fakestore.util

import android.app.Activity
import android.app.AlertDialog
import com.example.fakestore.R

class LoadingDialog(val mActivity:Activity) {
    private lateinit var isdialog:AlertDialog
    fun startLoading(){
        //Seteamos la vista
        val infalter = mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.loading,null)
        //Seteamos Dialog
        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog = builder.create()
        isdialog.show()
    }

    fun isDismiss(){
        isdialog.dismiss()
    }
}