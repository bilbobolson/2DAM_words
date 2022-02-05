package com.pmdm.words

/**
 * Antonio José Sánchez Bujaldón
 * Programación de Aplicaciones Multimedia y de Dispositivos Móviles
 * curso 2021|22
 */

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pmdm.words.adaptadores.CellAdapter
import com.pmdm.words.databinding.ActivityMainBinding
import com.pmdm.words.databinding.AppbarLayoutBinding
import com.pmdm.words.modelos.Table

class MainActivity : AppCompatActivity()
{
    private lateinit var adaptador: CellAdapter
    private lateinit var binding: ActivityMainBinding

    /**
     * @param savedInstanceState: Bundle?
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // bindeo del layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // modificar la barra de aplicación
        supportActionBar?.apply {

            // indicamos que vamos a customizar la barra de aplicación
            setDisplayShowCustomEnabled(true)

            // ocultamos el nombre de la aplicación
            setDisplayShowTitleEnabled(false)

            // cambiamos el color de fondo
            //val background = ColorDrawable(Color.parseColor("#f12c13"))
            setBackgroundDrawable(ColorDrawable(Color.WHITE))

            // añadir el título de la aplicación centrado
            setCustomView(AppbarLayoutBinding.inflate(layoutInflater).root)
        }

        var tablero: Table = Table(binding.wordsTablero)

        tablero.writeChar(1,1,"G")
        tablero.setColor(1,1,"#34eb4c")
        tablero.writeWord(0, "CANOA")

        binding.wordsGo.setOnClickListener {
            tablero.reset()
            //datos[0].color = "#34eb4c"
            //adaptador.notifyItemChanged(0)
        }
    }
}