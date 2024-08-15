package com.example.quoteapp.Data

import android.content.Context
import android.content.Intent

fun shareFun(context:Context,content:String){

    val intent = Intent().apply {
        this.action = Intent.ACTION_SEND
        this.type = "text/plain"
        this.putExtra(Intent.EXTRA_TEXT,content)
    }

    context.startActivity(
        Intent.createChooser(
            intent,"Share Quote"
        )
    )
}