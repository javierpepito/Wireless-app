package com.example.wireless_app

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ToDoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_to_do)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = Firebase.firestore

        val todo = hashMapOf(
            "title" to "Tarea 1",
            "description" to "Estudiar sobre control de oleadas .",
            "state" to "false"
        )

        db.collection("todo").add(todo)
            .addOnSuccessListener { documentReference ->
                Log.d("Firebase", "Tuvo exito. $documentReference")
            }
            .addOnFailureListener { error ->
                Log.w("Firebase", "Ocurrio un fallo.", error)
            }

        db.collection("todo").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Firebase", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Firebase", "Hubo un error al insertar el documento.", exception)
            }

    }
}