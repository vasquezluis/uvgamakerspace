package com.luivasq.spacem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.luivasq.spacem.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    /* configurar viewBing */
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* binding */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScanner.setOnClickListener { initScaner() }
    }

    private fun initScaner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Scan QR de Maker Space")
        // integrator.setTorchEnabled(true)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result:IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null){
            if(result.contents == null){
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                //Toast.makeText(this, "El valor escaneado es: ${result.contents}", Toast.LENGTH_LONG).show()
                if(result.contents == "1"){
                    // Toast.makeText(this, "Escaneaste el valor 1", Toast.LENGTH_LONG).show()
                    // crear objeto para el fragmento creado (pop window)
                    var dialog = customDialogFragment()
                    dialog.show(supportFragmentManager, "customDialog")
                } else if(result.contents == "2"){
                    Toast.makeText(this, "Escaneaste el valor 2", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }


    }

}