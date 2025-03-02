package com.example.ticket_booking.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ticket_booking.R
import com.example.ticket_booking.databinding.ItemTimeBinding

class TimeAdapter(private val timeSlots:List<String>):RecyclerView.Adapter<TimeAdapter.ViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class ViewHolder(private val binding:ItemTimeBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(time:String){
            binding.timeTextView.text = time
            if(selectedPosition==position){
                binding.timeTextView.setBackgroundColor(R.drawable.white_bg)
                binding.timeTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
            }else{
                binding.timeTextView.setBackgroundColor(R.drawable.light_black_bg)
                binding.timeTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            }

            binding.root.setOnClickListener {
                val position=position
                if(position!=RecyclerView.NO_POSITION){
                    lastSelectedPosition = selectedPosition
                    selectedPosition=position
                    notifyItemChanged(lastSelectedPosition)
                    notifyItemChanged(selectedPosition)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = timeSlots.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(timeSlots[position])
    }
}