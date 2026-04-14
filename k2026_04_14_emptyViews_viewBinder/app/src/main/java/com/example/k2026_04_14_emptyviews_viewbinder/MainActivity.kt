package com.example.k2026_04_14_emptyviews_viewbinder

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.k2026_04_14_emptyviews_viewbinder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binder : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binder = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binder.root)

        //binder.myButton.setText(getString(R.string.select_me))
        binder.myButton.setOnClickListener {
            Log.i("PGB", "In onClick Listener")
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
        }

    }
}