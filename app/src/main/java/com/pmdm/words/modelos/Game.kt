package com.pmdm.words.modelos

/**
 * Antonio José Sánchez Bujaldón
 * Programación de Aplicaciones Multimedia y de Dispositivos Móviles
 * curso 2021|22
 */

import com.pmdm.words.Constants
import com.pmdm.words.databinding.ActivityMainBinding

class Game(val binding: ActivityMainBinding)
{
    private var tries: Int = Constants.INTENTOS
    private lateinit var word: String

    private var board: Table? = null

    fun start()
    {
        // reseteamos el número de intentos
        tries = Constants.INTENTOS

        // crear/resetear el tablero
        //if (board==null) board = Table(binding.wordsTablero)
        //else             board!!.reset()

        // esta línea hace lo mismo que la sentencia if anterior
        board?.reset() ?: run { board = Table(binding.wordsTablero) }

        // seleccionamos la palabra con la que jugar
        word = ""

    }

}