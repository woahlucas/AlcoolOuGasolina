package com.example.alcoolougasolina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Switch

class MainActivity : AppCompatActivity() {
    var percentual:Double = 0.7
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            percentual = savedInstanceState.getDouble("percentual", 0.7)
        }

        val switchPercentual: Switch = findViewById(R.id.swPercentual)
        val resultadoTextView: TextView = findViewById(R.id.resultado)

        switchPercentual.setOnCheckedChangeListener { _, isChecked ->
            percentual = if (isChecked) 0.70 else 0.75
            switchPercentual.text = if (isChecked) "70%" else "75%"
            Log.d("PDM23", "Valor percentual atualizado para $percentual")
        }

        Log.d("PDM23","No onCreate, $percentual")

        val btCalc: Button = findViewById(R.id.btCalcular)
        btCalc.setOnClickListener(View.OnClickListener {
            val valorGasolina: EditText = findViewById(R.id.edGasolina)
            val valorAlcool: EditText = findViewById(R.id.edAlcool)
            var textoGasolina = valorGasolina.text.toString()
            val textoAlcool = valorAlcool.text.toString()

            if (textoGasolina.isNotEmpty() && textoAlcool.isNotEmpty()) {
                val precoGasolina = textoGasolina.toDouble()
                val precoAlcool = textoAlcool.toDouble()

                val resultado = calcularMelhorCombustivel(precoGasolina, precoAlcool, percentual)
                resultadoTextView.text = resultado
            } else {
                resultadoTextView.text = "Preencha os valores de gasolina e álcool."
            }

            Log.d("PDM23","No btCalcular, $percentual")
            Log.d("PDM23","$textoGasolina")
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("percentual", percentual)
        super.onSaveInstanceState(outState)
    }

    private fun calcularMelhorCombustivel(precoGasolina: Double, precoAlcool: Double, percentual: Double): String {
        return if (precoAlcool <= precoGasolina * percentual) {
            "Melhor usar Álcool"
        } else {
            "Melhor usar Gasolina"
        }
    }

    override fun onResume(){
        super.onResume()
        Log.d("PDM23","No onResume, $percentual")
    }
    override fun onStart(){
        super.onStart()
        Log.d("PDM23","No onStart, $percentual")
    }
    override fun onPause(){
        super.onPause()
        Log.d("PDM23","No onPause, $percentual")
    }
    override fun onStop(){
        super.onStop()
        Log.d("PDM23","No onStop, $percentual")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d("PDM23","No onDestroy, $percentual")
    }
}