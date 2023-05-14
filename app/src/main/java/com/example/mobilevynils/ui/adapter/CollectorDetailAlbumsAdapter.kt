package com.example.mobilevynils.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mobilesvynilis.models.Album
import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.CollectorDetailAlbumsItemBinding

class CollectorDetailAlbumsAdapter(album_list: List<Album>) :
    RecyclerView.Adapter<CollectorDetailAlbumsAdapter.CollectorDetailAlbumsViewHolder>() {

    private var albumList: List<Album> = album_list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorDetailAlbumsViewHolder {
        val withDataBinding: CollectorDetailAlbumsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorDetailAlbumsViewHolder.LAYOUT,
            parent,
            false
        )
        return CollectorDetailAlbumsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorDetailAlbumsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.albums = albumList[position]

        }
        holder.bind(albumList[position])
        holder.viewDataBinding.root.setOnClickListener {
            // val action = AlbumFragmentDirections.actionAlbumFragmentToCommentFragment(albums[position].albumId)
            // Navigate using that action
            //holder.viewDataBinding.root.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return albumList.size
    }


    class CollectorDetailAlbumsViewHolder(val viewDataBinding: CollectorDetailAlbumsItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_detail_albums_item
        }
        fun bind(album: Album) {
            Glide.with(itemView)
                .load(album.cover)
                .apply(
                    RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.ic_launcher_foreground))
                .into(viewDataBinding.albumImage)
        }


    }

}