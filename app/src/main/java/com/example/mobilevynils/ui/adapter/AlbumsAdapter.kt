package com.example.mobilevynils.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mobilesvynilis.models.Album
import com.example.mobilevynils.AlbumFragmentDirections
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

        holder.viewDataBinding.albumItemImageView.contentDescription="orale"
        Glide.with(holder.viewDataBinding.root.context)
            .load(albums[position].cover)
            .placeholder(R.drawable.ic_launcher_foreground)

            .into(holder.viewDataBinding.albumItemImageView)

    }

    holder.viewDataBinding.root.setOnClickListener {
        Log.i("AlbumSelected", "Album numero ${albums[position]}")
        //Navigation.createNavigateOnClickListener(R.id.album_list_to_detail, null)
       val bundle = bundleOf("Albums" to  albums[position].name)
        val action = AlbumFragmentDirections.albumListToDetail()
        // Navigate using that action
        holder.viewDataBinding.root.findNavController().navigate(action.actionId, bundle)
    }
}
    override fun getItemCount(): Int {
        return albums.size
    }


    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        val imagenAlbum: ImageView = viewDataBinding.albumItemImageView
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }

    }


}