package com.example.tsdc_vinilos_equipo6.ui.adapters

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.ArtistPrizesListItemsBinding
import com.example.mobilevynils.models.Prize

class ArtistPrizesAdapter(prize_list: List<Prize>) :
    RecyclerView.Adapter<ArtistPrizesAdapter.ArtistPrizesViewHolder>() {

    private var prizeList: List<Prize> = prize_list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistPrizesViewHolder {
        val withDataBinding: ArtistPrizesListItemsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistPrizesViewHolder.LAYOUT,
            parent,
            false
        )
        return ArtistPrizesViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistPrizesViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.ArtistPrize.text = prizeList[position].name
            it.PrizeDescription.text = prizeList[position].description
        }
        holder.viewDataBinding.root.setOnClickListener {
            // val action = AlbumFragmentDirections.actionAlbumFragmentToCommentFragment(albums[position].albumId)
            // Navigate using that action
            //holder.viewDataBinding.root.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return prizeList.size
    }

    class ArtistPrizesViewHolder(val viewDataBinding: ArtistPrizesListItemsBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_prizes_list_items
        }
    }

}