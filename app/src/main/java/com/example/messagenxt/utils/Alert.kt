package com.example.messagenxt.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext


fun alert(message:String,context:Context){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}