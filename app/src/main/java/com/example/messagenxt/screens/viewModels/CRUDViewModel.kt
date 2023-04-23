package com.example.messagenxt.screens.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.messagenxt.data.Chat
import com.example.messagenxt.utils.alert
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CRUDViewModel() : ViewModel() {
    fun saveData(
        chat: Chat,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch{
        val firestoreRef =Firebase.firestore.collection(chat.from).document(chat.to).collection(chat.time).document(chat.message)
        try {
            firestoreRef.set(chat).addOnSuccessListener {
                alert("message sent to ${chat.to}",context)
            }
        }catch (e:Exception){
            alert(e.message.toString(),context)
        }
    }

    fun retreiveData(
        to:String,
        from:String,
        context: Context,
        data:(Chat)-> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{
        val firestoreRef =Firebase.firestore.collection(to).document(from)
        try {
            firestoreRef.get().addOnSuccessListener {
                if (it.exists()){
                    val chat = it.toObject<Chat>()!!
                    data(chat)
                }else{
                    alert("no messages",context)
                }
            }
        }catch (e:Exception){
            alert(e.message.toString(),context)
        }
    }

    fun deleteData(
        context: Context,
        to: String,
        from: String
    )= CoroutineScope(Dispatchers.IO).launch{
        val firestoreRef = Firebase.firestore.collection(to).document(from)
        try {
            firestoreRef.delete().addOnSuccessListener {
                alert("message from $from deleted",context)
            }
        }catch (e:Exception){
            alert(e.message.toString(),context)
        }

    }
}