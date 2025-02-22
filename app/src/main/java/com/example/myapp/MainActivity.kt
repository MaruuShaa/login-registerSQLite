package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnClose = findViewById<Button>(R.id.btn_keluar)

        btnClose.setOnClickListener {
            finish()
        }

        val kalkulator = findViewById<Button>(R.id.btn_kalku)

        kalkulator.setOnClickListener {
            val intent = Intent(this,Kalkulator::class.java)
            startActivity(intent)
        }

        val note = findViewById<Button>(R.id.btn_note)

        note.setOnClickListener {
            val intent = Intent(this,NoteActivity::class.java)
            startActivity(intent)
        }
    }
}