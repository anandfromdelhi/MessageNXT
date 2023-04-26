package com.example.messagenxt.screens.viewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.messagenxt.data.Chat
import com.example.messagenxt.data.Messages
import com.example.messagenxt.data.User
import com.example.messagenxt.utils.alert
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CRUDViewModel() : ViewModel() {

    fun createUserInDatabase(
        user: User
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore.collection("users").document(user.userEmail)
        try {
            firestoreRef.set(user).addOnSuccessListener {
                Log.d("USER", "createUserInDatabase: ${user.userName} added in firestore")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("USER", "createUserInDatabase: ${e.message}")
        }

    }

    fun getUsersList(
        context: Context,
        listOfUsers: (List<User?>) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore.collection("users")
        try {
            firestoreRef.get().addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val users = querySnapshot.documents
                    val usersList = users.map { it.toObject(User::class.java) }
                    listOfUsers(usersList)
                } else {

                    alert("no users found", context)
                }

            }
        } catch (e: Exception) {
            Log.d("USER", "getUsersList: ${e.message}")
        }
    }

    fun addMessageToDatabase(
        message: Messages,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore.collection(message.from).document(message.time)

        try {
            firestoreRef.set(message)
                .addOnSuccessListener { Log.d("addMessageToDatabase2", "message added to database") }
        } catch (e: Exception) {
            Log.d("addMessageToDatabase", "addMessageToDatabase: ${e.message}")
        }
    }

    fun addMessageToDatabase2(
        message: Messages,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore.collection(message.to).document(message.time)

        try {
            firestoreRef.set(message)
                .addOnSuccessListener { Log.d("addMessageToDatabase2", "message added to database") }
        } catch (e: Exception) {
            Log.d("addMessageToDatabase2", "addMessageToDatabase: ${e.message}")
        }
    }

    fun saveData(
        chat: Chat,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore.collection("${chat.from}" + "-" + "${chat.to}").document(chat.time)
        try {
            firestoreRef.set(chat).addOnSuccessListener {
                alert("message sent to ${chat.to}", context)
            }
        } catch (e: Exception) {
            alert(e.message.toString(), context)
        }
    }

    fun readMessagesFromDatabase(
        from:String,
        context: Context,
        list: (List<Messages?>) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore.collection(from)

        try {
            firestoreRef.get().addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val message = querySnapshot.documents
                    val listOfMessages = message.map { it.toObject(Messages::class.java) }
                    list(listOfMessages)
                }

            }
        } catch (e: Exception) {
            alert("no messages to show", context)
        }

    }



    fun retreiveData(
        to: String,
        from: String,
        context: Context,
        list: (List<Chat?>) -> Unit,
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore.collection("$from-$to")
        try {
            firestoreRef.get().addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val chat = querySnapshot.documents
                    val listofChats = chat.map { it.toObject(Chat::class.java) }
                    list(listofChats)
                } else {
                    alert("no messages", context)
                }
            }
        } catch (e: Exception) {
            alert(e.message.toString(), context)
        }
    }

    fun deleteData(
        context: Context,
        to: String,
        from: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore.collection(to).document(from)
        try {
            firestoreRef.delete().addOnSuccessListener {
                alert("message from $from deleted", context)
            }
        } catch (e: Exception) {
            alert(e.message.toString(), context)
        }

    }
}