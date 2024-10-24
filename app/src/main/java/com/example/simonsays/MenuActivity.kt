package com.example.simonsays

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MenuActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)

        val startButton = findViewById<ImageView>(R.id.startButton)
        val exitButton = findViewById<ImageView>(R.id.exitButton)

        startButton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            // Iniciar la actividad
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finish()
        }
    }
}