package com.example.mobilevynils.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilevynils.models.Album
import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.AlbumItemBinding

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    var albums: List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // inflar el diseño del elemento de la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false
        )
        return AlbumViewHolder(binding)
    }

    // enlazar los datos con el diseño del elemento de la lista
    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.album = albums[position]

        // agregar un listener de clics en el elemento de la lista
        holder.binding.root.setOnClickListener {
            // hacer algo cuando el elemento de la lista sea clickeado
        }
    }

    // devolver el número de elementos en la lista
    override fun getItemCount(): Int {
        return albums.size
    }

    // definir la vista de cada elemento de la lista
    class AlbumViewHolder(val binding: AlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }
}