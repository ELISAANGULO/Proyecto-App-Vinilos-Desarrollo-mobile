package com.example.mobilevynils.ui.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobilesvynilis.models.Artista
import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.ArtistasItemBinding
import com.example.mobilevynils.utils.DateUtils
import com.example.mobilevynils.utils.ImageUtils
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


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

    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    private val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    fun formatDate(dateStr: String?): String? {
        if (dateStr == null) {
            return null
        }

        try {
            val date = inputFormat.parse(dateStr)
            return outputFormat.format(date!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    override fun onBindViewHolder(holder: ArtistaViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artista = artistas[position]

            holder.viewDataBinding.imgArtista.contentDescription="orale"
            Glide.with(holder.viewDataBinding.root.context)
                .load(artistas[position].image)
                .placeholder(R.drawable.ic_launcher_foreground)

                .into(holder.viewDataBinding.imgArtista)

        }



        holder.viewDataBinding.root.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        return artistas.size
    }


    class ArtistaViewHolder(val viewDataBinding: ArtistasItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        val imagenArtista: ImageView = viewDataBinding.imgArtista
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artistas_item
        }
    }


}