package com.example.k2026_04_21_reactive_binders

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.k2026_04_21_reactive_binders.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binder: ActivityMainBinding
    private var screenText: String = "Hello world"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binder = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binder.root)

        binder.button.setOnClickListener {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
            screenText += " ! "
            binder.textView.setText(screenText)
        }

    }
}