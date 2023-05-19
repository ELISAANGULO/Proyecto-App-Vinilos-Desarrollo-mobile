package com.example.tsdc_vinilos_equipo6.ui.adapters

import android.util.Log
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mobilesvynilis.models.Album
import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.ArtistAlbumsListItemsBinding

class ArtistAlbumsAdapter(album_list: List<Album>) :
    RecyclerView.Adapter<ArtistAlbumsAdapter.ArtistAlbumsViewHolder>() {

    private var albumList: List<Album> = album_list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAlbumsViewHolder {
        val withDataBinding: ArtistAlbumsListItemsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistAlbumsViewHolder.LAYOUT,
            parent,
            false
        )
        return ArtistAlbumsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistAlbumsViewHolder, position: Int) {
        holder.bind(albumList[position])
        holder.viewDataBinding.also {
            it.ArtistName.text = albumList[position].name
        }
        holder.viewDataBinding.root.setOnClickListener {
            // val action = AlbumFragmentDirections.actionAlbumFragmentToCommentFragment(albums[position].albumId)
            // Navigate using that action
            //holder.viewDataBinding.root.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return albumList.size
    }


    class ArtistAlbumsViewHolder(val viewDataBinding: ArtistAlbumsListItemsBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_albums_list_items
        }

        fun bind(album: Album) {
            Glide.with(itemView)
                .load(album.cover.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_album)
                )
                .into(viewDataBinding.AlbumImage)
        }
    }

}