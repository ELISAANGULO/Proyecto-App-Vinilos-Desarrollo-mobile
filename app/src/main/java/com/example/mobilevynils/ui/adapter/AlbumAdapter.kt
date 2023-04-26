package com.example.mobilevynils.ui.adapter

import android.graphics.PointF
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.AlbumItemBinding
import com.example.mobilesvynilis.models.Album


class AlbumAdapter:RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    var album :Album? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.albums = album


    }

    override fun getItemCount(): Int {
        return 1
    }


    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :

        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }

    }
}