package com.example.mobilevynils.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesvynilis.models.Collector
import androidx.navigation.findNavController

import com.example.mobilevynils.CollectorFragmentDirections


import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.CollectorItemBinding


class CollectorsAdapter : RecyclerView.Adapter<CollectorsAdapter.CollectorViewHolder>(){

    var collectors :List<Collector> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val withDataBinding: CollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorViewHolder.LAYOUT,
            parent,
            false)
        return CollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collectors[position]
            holder.viewDataBinding.root.setOnClickListener {

            }



        }





        holder.viewDataBinding.root.setOnClickListener {
            Log.i("CollectorSelected", "Collector numero ${collectors[position]}")
            //Navigation.createNavigateOnClickListener(R.id.collector_list_to_detail, null)
            //val bundle = bundleOf("Collectors" to  collectors[position].name)
            val action = CollectorFragmentDirections.actionCollectorFragmentToCollectorDetailFragment(collectors[position].collectorId)
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }


    class CollectorViewHolder(val viewDataBinding: CollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_item
        }
    }


}