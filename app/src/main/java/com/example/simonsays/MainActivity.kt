package com.example.simonsays

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // Declaración de las variables
    private lateinit var gridView: GridView
    private var level = 1
    private var imageAdapter: SimonAdapter? = null
    private var colorCorrect = mutableListOf<Int>()
    private var currentStep = 0
    private lateinit var startButton: Button

    @SuppressLint("MissingInflatedId")

    // Método onCreate()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        // Inicialización de las vistas
        gridView = findViewById(R.id.gridView)
        startButton = findViewById(R.id.buttonStart)
        imageAdapter = SimonAdapter(this)

        // Configuración del adaptador y la vista de la cuadrícula
        gridView.adapter = imageAdapter
        gridView.isEnabled = false
        gridView.alpha = 0.5f

        // Inicio del juego
        startGame()
    }

    // Método para iniciar el juego
    private fun startGame() {
        level = 1
        startButton.isEnabled = true
        startButton.alpha = 1f
        startButton.setOnClickListener {
            startNewLevel()
            startButton.isEnabled = false
            startButton.alpha = 0.5f
        }
    }

    // Método para iniciar un nuevo nivel
    private fun startNewLevel() {
        gridView.isEnabled = true
        gridView.alpha = 1f
        colorCorrect.add(Random.nextInt(0, 4))
        currentStep = 0
        displayColors(0)
    }

    // Método para mostrar los colores en el juego
    private fun displayColors(index: Int) {

        gridView.isEnabled = false
        if (index < colorCorrect.size) {
            Handler(mainLooper).postDelayed({
                imageAdapter?.updateImage(colorCorrect[index])
                displayColors(index + 1)
            }, 1200)
        } else {
            enableUserInput()
        }
    }

    // Método para habilitar la entrada del usuario
    private fun enableUserInput() {

        // Obtener la referencia al TextView para mostrar el nivel
        val score: TextView = findViewById(R.id.txtNivel)
        gridView.isEnabled = true

        // Configurar el escuchador de clics en la vista de la cuadrícula
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (currentStep < colorCorrect.size) {

                // Verificar si el color seleccionado es correcto
                if (colorCorrect[currentStep] == position) {
                    currentStep++
                    if (currentStep == colorCorrect.size) {
                        level++
                        startNewLevel()
                        score.text = "Score: " + (level - 1).toString()
                    }
                } else {

                    // Mostrar un mensaje de error si el color seleccionado es incorrecto
                    Toast.makeText(this, "Felicidades! Has llegado al nivel " + (level - 1), Toast.LENGTH_LONG).show()
                    gridView.isEnabled = false
                    gridView.alpha = 0.5f
                    currentStep = 0
                    colorCorrect.clear()

                    val scoreN = 0;
                    score.text = "Score $scoreN"

                    finish()
                }
            }
        }
    }
}