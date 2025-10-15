package com.example.wireless_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Se definen el tipo de variables.
    private lateinit var inputTexto: EditText
    private lateinit var btnEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // uso de locat para informar desde la consola.
        Log.d("MainActivity", "Inicio La aplicación")

        //Se conecta la variable de inputexto al componente que existe en el layout con id, se define la variable.
        inputTexto = findViewById(R.id.inputTexto)
        btnEnviar = findViewById(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            val texto = inputTexto.text.toString().ifBlank { "Sin texto" }
            val intent = Intent(this, DetalleActivity::class.java).apply {
                putExtra("EXTRA_TEXTO", texto)
            }
            startActivity(intent)
        }
    }
}
