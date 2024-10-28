package com.example.simonsays

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MenuActivity: AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)

        val startButton = findViewById<ImageView>(R.id.startButton)
        val exitButton = findViewById<ImageView>(R.id.exitButton)

        // Inicialización de la música de fondo
        mediaPlayer = MediaPlayer.create(this, R.raw.simon_soundtrack)
        mediaPlayer.start()

        // Configurar el botón de inicio
        startButton.setOnClickListener {
            // Crear un nuevo Intent para iniciar la actividad MainActivity
            val intent = Intent(this, MainActivity::class.java)
            // Iniciar la actividad
            startActivity(intent)
        }

        // Configurar el botón de salida
        exitButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.release()
            finish()
        }
    }
}