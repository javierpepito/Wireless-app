package com.example.wireless_app

import android.Manifest
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalleActivity : AppCompatActivity() {

    private lateinit var txtDetalle: TextView
    private lateinit var btnSolicitarPermiso: Button

    fun solicitarPermiso() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.ACCESS_WIFI_STATE
            ),
            1
        )
    }
    fun obtenerBluetooth(){
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        val dispositivosVinculados = bluetoothAdapter?.bondedDevices
        dispositivosVinculados?.forEach { dispositivo ->
            Log.d(
                "Bluetooth",
                "Nombre: ${dispositivo.name}," +
                        " Dirección: ${dispositivo.address}")
        }
    }
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnSolicitarPermiso)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtDetalle = findViewById(R.id.txtDetalle)
        btnSolicitarPermiso = findViewById(R.id.btnSolicitarPermiso)

        val recibido = intent.getStringExtra("EXTRA_TEXTO").orEmpty()
        txtDetalle.text = recibido

        btnSolicitarPermiso.setOnClickListener {
            val alerta = AlertDialog.Builder(this)
                .setTitle("¿Por qué pedimos este permiso?")
                .setMessage("Necesitamos acceso a ubicación para escanear dispositivos cercanos vía Bluetooth.")
                .setPositiveButton("Continuar") { _, _ ->
                    solicitarPermiso()
                }
                .setNegativeButton("Cancelar", null)
                .show()
            alerta.setOnDismissListener {
                val intent = Intent( this, BluetoothActivity::class.java)
                startActivity(intent)
            }
        }

    }

}