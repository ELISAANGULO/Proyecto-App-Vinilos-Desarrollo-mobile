package com.example.mobilevynils.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesvynilis.models.Artista
import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.ArtistasItemBinding

class ArtistaAdapater : RecyclerView.Adapter<ArtistaAdapater.ArtistaViewHolder>(){

    var artistas :List<Artista> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistaViewHolder {
        val withDataBinding: ArtistasItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistaViewHolder.LAYOUT,
            parent,
            false)
        return ArtistaViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistaViewHolder, position: Int) {
         holder.viewDataBinding.also {
             it.artista = artistas[position]
         }

         holder.viewDataBinding.root.setOnClickListener {

         }
    }

    override fun getItemCount(): Int {
        return artistas.size
    }


    class ArtistaViewHolder(val viewDataBinding: ArtistasItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artistas_item
        }
    }


}