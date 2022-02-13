package com.pmdm.words.modelos

import android.util.Log
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pmdm.words.Constants
import com.pmdm.words.R
import com.pmdm.words.databinding.ActivityMainBinding
import com.pmdm.words.modelos.Table
import kotlin.properties.Delegates.observable

class Game(val binding: ActivityMainBinding)
{
    // inetntos
    private var tries: Int = 0

    // palabra a adivinar
    private lateinit var word: Word

    // palabra en juego
    private lateinit var played: MutableList<String>

    // colores de la palabra en juego
    private lateinit var color:MutableList<String>

    // tablero de juego
    private var board: Table? = null

    // número de aciertos
    private var hits: Int = 0

    private var gameover: Boolean by observable(false) {
        property,oldValue,newValue ->

        // si el juego ha finalizado...
        if (newValue==true) {

            // mostramos ventana de fin de juego
            val madb = MaterialAlertDialogBuilder(binding.root.context)
            madb.setTitle(R.string.app_name)
                .setMessage("Game Over")
                .setPositiveButton("EMPEZAR") { d, w ->

                    // si elegimos jugar de nuevo
                    // reiniciamos las variables necesarias
                    tries = 0
                    word.palabra = "VIEJO"
                    board!!.reset()
                    gameover = false

                }
            madb.show()
        }
    }

    fun start()
    {
        // reseteamos el número de intentos
        tries = 0

        // crear/resetear el tablero
        //if (board==null) board = Table(binding.wordsTablero)
        //else             board!!.reset()

        // esta línea hace lo mismo que la sentencia if anterior
        board?.reset() ?: run { board = Table(binding.wordsTablero) }

        // seleccionamos la palabra con la que jugar
        word = Word(0, "CANOA", 0)


        // definimos el listener para el botón GO
        binding.apply {
            wordsGo.setOnClickListener {
                if (wordsPalabra.text?.isNotEmpty() == true)
                {
                    // recuperamos la palabra
                    var temp = wordsPalabra.text
                                           .toString()
                                           .uppercase()

                    //
                    played = Word.splitWord(temp)
                    Log.i("JUEGO", played.toString())

                    // reiniciamos el array de colores
                    color = MutableList(Constants.LETRAS_PALABRA) { Constants.F }

                    // TODO #####
                    word.chunks = Word.splitWord(word.palabra)

                    // comprobar qué letras están en su sitio
                    hits()

                    if (hits!=Constants.LETRAS_PALABRA) coincidences()

                    // escribimos la palabra en el tablero
                    board!!.writeWord(tries, temp, color)

                    // incrementamos el número de intentos
                    tries++

                    // si hemos terminado el juego
                    if (tries == Constants.INTENTOS)
                        gameover = true
                }
            }
        }
    }

    /**
     * Comprobamos si hay coincidencias entre la palabra original (word)
     * y la que se está jugando (played)
     */
    private fun hits()
    {
        hits = 0    // inicializamos a cero el contador de aciertos

        played.forEachIndexed { i, c ->

           if (c == word.getLetter(i))
           {
               hits++
               color[i]  = Constants.H

               // marcamos el caracter examinado en la palabra original
               // y en la que está en juego.
               word.marking(i)
               played[i] = Constants.MARK
           }
       }

        Log.i("JUEGO", "\nCOLORES ${color}\nORIGINAL ${word.chunks}\nPALABRA ${played}")
    }

    /**
     */
    private fun coincidences()
    {
        played.forEachIndexed { i, c ->

            if (c!=Constants.MARK)
            {
                // buscamos el carácter en word
                var idx = word.findLetter(c)

                // si lo encontramos...
                if (idx >= 0)
                {
                    color[i] = Constants.C

                    // marcamos el caracter examinado en la palabra original
                    // y en la que está en juego.
                    word.marking(idx)
                    played[i] = Constants.MARK
                }
            }
        }

        Log.i("JUEGO", "\nCOLORES ${color}\nORIGINAL ${word.chunks}\nPALABRA ${played}")
    }

}