package com.example.mobilevynils.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mobilesvynilis.models.Album
import com.example.mobilesvynilis.models.Track
import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.AlbumDetailItemBinding


class AlbumAdapter: RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    var album : Album? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var tracks : List<Track>? = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = album
        }
        if(album != null){
            Log.i("TieneTracks","${album!!.tracks}")
            holder.bind(album!!)
        }





    }

    override fun getItemCount(): Int {
        return 1
    }


    class AlbumViewHolder(val viewDataBinding: AlbumDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_detail_item
        }
        fun bind(album: Album) {


            Glide.with(itemView)
                .load(album.cover)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.vicente))
                .into(viewDataBinding.AlbumCover)
        }
    }

}