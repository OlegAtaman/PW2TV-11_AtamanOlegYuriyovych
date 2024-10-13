package com.example.laba2_proj_remake

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declare UI components
    private lateinit var editTextA: EditText
    private lateinit var editTextB: EditText
    private lateinit var editTextC: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var textViewResult: TextView
    private lateinit var textViewResult1: TextView
    private lateinit var textViewResult2: TextView
    private lateinit var textViewResulte: TextView
    private lateinit var textViewResulte1: TextView
    private lateinit var textViewResulte2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view to the updated layout
        setContentView(R.layout.activity_main)

        // Initialize UI components
        editTextA = findViewById(R.id.editTextA)
        editTextB = findViewById(R.id.editTextB)
        editTextC = findViewById(R.id.editTextC)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        textViewResult = findViewById(R.id.textViewResult)
        textViewResult1 = findViewById(R.id.textViewResult1)
        textViewResult2 = findViewById(R.id.textViewResult2)
        textViewResulte = findViewById(R.id.textViewResulte)
        textViewResulte1 = findViewById(R.id.textViewResulte1)
        textViewResulte2 = findViewById(R.id.textViewResulte2)

        // Set click listener for the calculate button
        buttonCalculate.setOnClickListener {
            calculateResult()
        }
    }

    private fun calculateD(qrl: Double, avin: Double, ar: Double, gvin: Double, etazu: Double, ktbs: Double): Double {
        return (1000000 / qrl) * avin * (ar / (100 - gvin)) * (1 - etazu) - ktbs
    }

    private fun calculateE(ktb: Double, qrib: Double, b: Double): Double {
        return 0.000001 * ktb * qrib * b
    }

    // Function to perform the calculation
    private fun calculateResult() {
        // Retrieve and validate input values
        val aText = editTextA.text.toString()
        val bText = editTextB.text.toString()
        val cText = editTextC.text.toString()

        if (aText.isEmpty() || bText.isEmpty() || cText.isEmpty()) {
            textViewResult.text = "Please enter values for a, b, and c."
            return
        }

        // Convert input strings to Double
        val a = aText.toDoubleOrNull()
        val b = bText.toDoubleOrNull()
        val c = cText.toDoubleOrNull()

        // Check for valid number inputs
        if (a == null || b == null || c == null) {
            textViewResult.text = "Invalid input. Please enter valid numbers."
            return
        }

        val etazu = 0.985
        val ktbs = 0.0

        val qrl1 = 20.47
        val avin1 = 0.8
        val ar1 = 25.20
        val gvin1 = 1.5

        val qrl2 = 39.48
        val avin2 = 1.0
        val ar2 = 0.15
        val gvin2 = 0.0

        val d = calculateD(qrl1, avin1, ar1, gvin1, etazu, ktbs)
        val d1 = calculateD(qrl2, avin2, ar2, gvin2, etazu, ktbs)

        val e = calculateE(d, qrl1, a)
        val e1 = calculateE(d1, qrl2, b)

        // Display the result
        textViewResult.text = "Показник емісії твердих частинок при спалюванні вугілля становитиме: $d т"
        textViewResulte.text = "Валовий викид при спалюванні вугілля становитиме: $e г/ГДж"
        textViewResult1.text = "Показник емісії твердих частинок при спалюванні мазуту становитиме: $d1 т"
        textViewResulte1.text = "Валовий викид при спалюванні мазуту становитиме: $e1 г/ГДж"
        textViewResult2.text = "Показник емісії твердих частинок при спалюванні природного газу становитиме: 0 т"
        textViewResulte2.text = "Валовий викид при спалюванні природного газу становитиме: 0 г/ГДж"
    }
}
