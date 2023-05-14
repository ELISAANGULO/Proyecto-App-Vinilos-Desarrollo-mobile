package com.example.mobilevynils.ui.adapter

import android.provider.MediaStore.Audio.Artists.Albums
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
import com.example.mobilesvynilis.models.Collector
import com.example.mobilesvynilis.models.Track
import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.AlbumDetailItemBinding
import com.example.mobilevynils.databinding.CollectorDetailItemBinding

class CollectorAdapter : RecyclerView.Adapter<CollectorAdapter.CollectorViewHolder>() {

    var collector : Collector? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var albums : List<Albums>? = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val withDataBinding: CollectorDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorViewHolder.LAYOUT,
            parent,
            false)
        return CollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collector
        }
        if(collector != null){
            Log.i("TieneAlbums","${collector!!.albums}")

        }

    }

    override fun getItemCount(): Int {
        return 1
    }


    class CollectorViewHolder(val viewDataBinding: CollectorDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_detail_item
        }

    }

}