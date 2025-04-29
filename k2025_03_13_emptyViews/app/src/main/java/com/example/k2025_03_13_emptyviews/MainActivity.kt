package com.example.k2025_03_13_emptyviews

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var btn: Button  = findViewById(R.id.select_button)
        btn.setOnClickListener{
            Toast.makeText(this, "Hello - you selected me", Toast.LENGTH_LONG).show()
        }
    }
}