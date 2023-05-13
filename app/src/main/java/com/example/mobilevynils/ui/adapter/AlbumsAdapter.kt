package com.example.mobilevynils.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mobilesvynilis.models.Album
import com.example.mobilevynils.AlbumFragmentDirections
import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.AlbumItemBinding
import com.example.mobilevynils.models.Performer

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>(){

    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsAdapter.AlbumsViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumsAdapter.AlbumsViewHolder.LAYOUT,
            parent,
            false)
        return AlbumsAdapter.AlbumsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.albums = albums[position]
        }
        //displayPerformers(albums[position].performers, holder.viewDataBinding.AlbumPerformer)
        holder.bind(albums[position])
        holder.viewDataBinding.root.setOnClickListener {
            val action = AlbumFragmentDirections.albumListToDetail(albums[position].albumId)
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }


    class AlbumsViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
        fun bind(album: Album) {
            Glide.with(itemView)
                .load(album.cover)
                .apply(RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.ic_launcher_foreground))
                .into(viewDataBinding.albumItemImageView)
        }
    }

    fun listPerformersToText(performerslist:List<Performer>?): String? {
        var texto: String? = null
        if (performerslist.isNullOrEmpty())
            texto = "No hay perfomers disponibles"
        else {
            for (p in performerslist) {
                if (texto == null)
                    texto = p.name
                else
                    texto = texto + ", " + p.name
            }
        }
        return texto
    }

    fun displayPerformers(performerslist:List<Performer>?, textView: TextView) {
        try {
            textView.text = listPerformersToText(performerslist)
        }catch (_: Exception){

        }
    }
}