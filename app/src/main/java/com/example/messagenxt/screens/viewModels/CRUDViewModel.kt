package com.example.messagenxt.screens.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.messagenxt.data.Chat
import com.example.messagenxt.utils.alert
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldPath
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
        val firestoreRef = Firebase.firestore.collection("${chat.from}"+"-"+"${chat.to}").document(chat.time)
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
        list:(List<Chat?>)-> Unit,
    ) = CoroutineScope(Dispatchers.IO).launch{
        val firestoreRef =Firebase.firestore.collection("$from-$to")
        try {
            firestoreRef.get().addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty){
                    val chat = querySnapshot.documents
                    val listofChats = chat.map{ it.toObject(Chat::class.java)}
                    list(listofChats)
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