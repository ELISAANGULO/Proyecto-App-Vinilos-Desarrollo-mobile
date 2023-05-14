package com.example.mobilevynils.ui.adapter

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
import com.example.mobilesvynilis.models.Artista
import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.ArtistDetailItemBinding

class ArtistAdapter: RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    var artist : Artista? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val withDataBinding: ArtistDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistViewHolder.LAYOUT,
            parent,
            false)
        return ArtistViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artist = artist
        }
        holder.bind(artist)
        holder.viewDataBinding.root.setOnClickListener {
            /*
            val action = ArtistsFragmentDirections.actionArtistFragmentToArtistDetailFragment(artist!!.artistId)
            // Navigate using that action
            //holder.viewDataBinding.root.findNavController().navigate(action)
            holder.viewDataBinding.root.findNavController().navigate(action)
             */
        }


    }

    override fun getItemCount(): Int {
        return 1
    }


    class ArtistViewHolder(val viewDataBinding: ArtistDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_detail_item
        }
        fun bind(artist: Artista?) {
            Log.d("ArtistaAdapterartist", artist.toString())
            if (artist != null) {
                Glide.with(itemView)
                    .load(artist.image.toUri().buildUpon().scheme("https").build())
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.drawable.ic_launcher_foreground))
                    .into(viewDataBinding.artistImage)
            }
        }
    }

}