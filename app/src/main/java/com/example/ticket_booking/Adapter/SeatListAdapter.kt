package com.example.ticket_booking.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ticket_booking.Model.Seat
import com.example.ticket_booking.R
import com.example.ticket_booking.databinding.ActivitySeatListBinding
import com.example.ticket_booking.databinding.SeatItemBinding

class SeatListAdapter(
    private val seatList:List<Seat>,
    private val context:Context,
    private val selectedSeat: SelectedSeat
):RecyclerView.Adapter<SeatListAdapter.ViewHolder>() {

    private val selectedSeatName=ArrayList<String>()


    class ViewHolder(val binding:SeatItemBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatListAdapter.ViewHolder {
        return ViewHolder(SeatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SeatListAdapter.ViewHolder, position: Int) {
        val seat=seatList[position]
        holder.binding.seat.text = seat.name

        when(seat.status){
            Seat.SeatStatus.AVAILABLE ->{
                holder.binding.seat.setBackgroundResource(R.drawable.ic_seat_available)
                holder.binding.seat.setTextColor(context.getColor(R.color.white))
            }
            Seat.SeatStatus.SELECTED ->{
                holder.binding.seat.setBackgroundResource(R.drawable.ic_seat_selected)
                holder.binding.seat.setTextColor(context.getColor(R.color.black))
            }
            Seat.SeatStatus.UNAVAILABLAE ->{
                holder.binding.seat.setBackgroundResource(R.drawable.ic_seat_unavailable)
                holder.binding.seat.setTextColor(context.getColor(R.color.grey))
            }

        }
        holder.binding.seat.setOnClickListener {
            when(seat.status){
                Seat.SeatStatus.AVAILABLE -> {
                    seat.status = Seat.SeatStatus.SELECTED
                    selectedSeatName.add(seat.name)
                    notifyItemChanged(position)
                }
                Seat.SeatStatus.SELECTED -> {
                    seat.status = Seat.SeatStatus.AVAILABLE
                    selectedSeatName.remove(seat.name)
                    notifyItemChanged(position)
                }
                else->{}
            }
            val selected = selectedSeatName.joinToString (",")

            selectedSeat.Return(selected, selectedSeatName.size)
        }
    }

    override fun getItemCount(): Int = seatList.size


    interface SelectedSeat{
        fun Return(selectedName: String, num:Int){}
    }
}