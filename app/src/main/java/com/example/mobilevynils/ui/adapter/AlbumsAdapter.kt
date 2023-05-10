package com.example.mobilevynils.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mobilesvynilis.models.Album
import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.AlbumItemBinding

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>(){

    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsAdapter.AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumsAdapter.AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumsAdapter.AlbumViewHolder(withDataBinding)
    }

override fun onBindViewHolder(holder: AlbumsAdapter.AlbumViewHolder, position: Int) {
    holder.viewDataBinding.also {
        it.album = albums[position]

        holder.viewDataBinding.imageView.contentDescription="orale"
        Glide.with(holder.viewDataBinding.root.context)
            .load(albums[position].cover)
            .placeholder(R.drawable.ic_launcher_foreground)

            .into(holder.viewDataBinding.imageView)

    }



    holder.viewDataBinding.root.setOnClickListener {

    }

}
    override fun getItemCount(): Int {
        return albums.size
    }


    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        val imagenAlbum: ImageView = viewDataBinding.imageView
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }

    }


}