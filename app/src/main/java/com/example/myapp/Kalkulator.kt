package com.example.myapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Kalkulator : AppCompatActivity() {

    private lateinit var display: TextView
    private var input = ""
    private var operator = ""
    private var operand1 = ""
    private var operand2 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kalkulator)

        // Mengatur edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi display
        display = findViewById(R.id.tvDisplay)

        // Inisialisasi tombol angka
        val numberButtons = listOf(
            findViewById<Button>(R.id.btn0),
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4),
            findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6),
            findViewById<Button>(R.id.btn7),
            findViewById<Button>(R.id.btn8),
            findViewById<Button>(R.id.btn9)
        )

        // Menambahkan event listener untuk tombol angka
        numberButtons.forEach { button ->
            button.setOnClickListener {
                input += button.text
                display.text = input
            }
        }

        // Inisialisasi tombol operator
        val operators = mapOf(
            R.id.btnPlus to "+",
            R.id.btnMinus to "-",
            R.id.btnMultiply to "*",
            R.id.btnDivide to "/"
        )

        operators.forEach { (id, symbol) ->
            findViewById<Button>(id).setOnClickListener {
                if (input.isNotEmpty()) {
                    operand1 = input
                    operator = symbol
                    input = ""
                }
            }
        }

        // Tombol desimal
        findViewById<Button>(R.id.btnDot).setOnClickListener {
            if (!input.contains(".")) {
                input += "."
                display.text = input
            }
        }

        // Tombol clear
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            input = ""
            operand1 = ""
            operand2 = ""
            operator = ""
            display.text = "0"
        }

        // Tombol backspace
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            if (input.isNotEmpty()) {
                input = input.dropLast(1)
                display.text = if (input.isNotEmpty()) input else "0"
            }
        }

        // Tombol equals (=)
        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            if (operand1.isNotEmpty() && operator.isNotEmpty() && input.isNotEmpty()) {
                operand2 = input
                val result = calculate(operand1.toDouble(), operand2.toDouble(), operator)
                display.text = result.toString()
                // Reset state
                input = result.toString()
                operand1 = ""
                operand2 = ""
                operator = ""
            }
        }


    }

    // Fungsi untuk menghitung hasil
    private fun calculate(op1: Double, op2: Double, operator: String): Double {
        return when (operator) {
            "+" -> op1 + op2
            "-" -> op1 - op2
            "*" -> op1 * op2
            "/" -> if (op2 != 0.0) op1 / op2 else Double.NaN
            else -> 0.0
        }
    }


}
