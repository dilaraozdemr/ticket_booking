package com.example.ticket_booking.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ticket_booking.Model.Cast
import com.example.ticket_booking.databinding.ViewholderCastBinding

class CastListAdapter(private val cast:ArrayList<Cast>):RecyclerView.Adapter<CastListAdapter.ViewHolder>() {

    private var context:Context?=null

    inner class ViewHolder(val binding:ViewholderCastBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(cast:Cast){
            context?.let {
                Glide.with(it)
                    .load(cast.PicUrl)
                    .into(binding.castImage)
            }
            binding.nameText.text = cast.Actor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastListAdapter.ViewHolder {
        context=parent.context
        val binding = ViewholderCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastListAdapter.ViewHolder, position: Int) {
        holder.bind(cast[position])
    }

    override fun getItemCount(): Int {
        return cast.size
    }
}