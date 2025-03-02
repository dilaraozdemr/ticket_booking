package com.example.ticket_booking.Model

data class Seat(var status:SeatStatus, var name:String){
    enum class SeatStatus{
        AVAILABLE, SELECTED, UNAVAILABLAE
    }
}
