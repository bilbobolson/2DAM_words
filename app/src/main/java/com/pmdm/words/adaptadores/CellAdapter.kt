package com.pmdm.words.adaptadores

/**
 * Antonio José Sánchez Bujaldón
 * Programación de Aplicaciones Multimedia y de Dispositivos Móviles
 * curso 2021|22
 */

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.pmdm.words.databinding.CeldaItemLayoutBinding
import com.pmdm.words.modelos.Cell

class CellAdapter(var datos: MutableList<Cell>):
        RecyclerView.Adapter<CellAdapter.CellHolder>()
{
    /**
     * Devolvemos al adaptador el tamaño de la colección de datos que debe
     * manejar.
     * @return
     */
    override fun getItemCount(): Int = datos.size


    /**
     * Infla un layout y todas sus vistas y crea un contenedor para ellas.
     * @param parent: ViewGroup
     * @param viewType: Int
     * @return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellHolder
    {
        val inflador = LayoutInflater.from(parent.context)
        var binding  = CeldaItemLayoutBinding.inflate(inflador, parent,false)

        return CellHolder(binding)
    }

    /**
     * Vincula un elemento de la colección de datos con un contenedor
     * previamente creado.
     * @param holder: CellHolder
     * @param position: Int
     */
    override fun onBindViewHolder(holder: CellHolder, position: Int)
    {
        holder.bindCell(datos[position])
    }

    /**
     * El contenedor guardará un layout inflado con todas sus vistas,
     * asociado a cada determinado elemento de la colección de datos.
     */
    inner class CellHolder(val binding: CeldaItemLayoutBinding):
                    RecyclerView.ViewHolder(binding.root)
    {
        fun bindCell(celda: Cell)
        {
            binding.celdaLetra.apply {
                text = celda.letra
                background = Color.parseColor(celda.color).toDrawable()
            }
        }
    }
}