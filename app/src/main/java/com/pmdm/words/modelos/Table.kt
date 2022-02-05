package com.pmdm.words.modelos

/**
 * Antonio José Sánchez Bujaldón
 * Programación de Aplicaciones Multimedia y de Dispositivos Móviles
 * curso 2021|22
 */

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pmdm.words.Constants
import com.pmdm.words.adaptadores.CellAdapter

class Table(val rv: RecyclerView)
{
    private var rows: Int = Constants.INTENTOS
    private var cols: Int = Constants.LETRAS_PALABRA

    // creamos la colección de celdas
    private var cells: MutableList<Cell> = MutableList(rows*cols) { Cell() }

    // creamos el adaptador y lo inicializamos
    private var cellAdapter: CellAdapter = CellAdapter(cells)

    /**
     * En el constructor asociamos el adaptador al recycler y definimos
     * el gestor de layout que vamos a utilizar (GridLayout).
     */
    init
    {

        rv.apply {
            adapter = cellAdapter
            layoutManager = GridLayoutManager(this.context, cols)
            setHasFixedSize(true)
        }
    }

    /**
     * @param r: Int
     * @param c: Int
     * @param char: String
     */
    fun writeChar(r: Int, c: Int, char: String)
    {
        val posicion = (r * cols) + c
        cells[posicion].letra = char
        cellAdapter.notifyItemChanged(posicion)
    }

    /**
     * @param r: Int
     * @param c: Int
     * @param color: String
     */
    fun setColor(r: Int, c: Int, color: String)
    {
        val posicion = (r * cols) + c
        cells[posicion].color = color
        cellAdapter.notifyItemChanged(posicion)
    }

    /**
     * Escribe la palabra completa en pantalla.
     * @param r: Int
     * @param word: String
     */
    fun writeWord(r: Int, word: String)
    {
        word.forEachIndexed { index, c ->
            writeChar(r,index,c.toString())
        }
    }

    /**
     * Reiniciar el tablero.
     */
    fun reset()
    {
        cells.forEachIndexed { i, item ->
            item.letra = ""
            item.color = Constants.V
            cellAdapter.notifyItemChanged(i)
        }
    }

}