package com.example.ticket_booking.Activity

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticket_booking.Adapter.DateAdapter
import com.example.ticket_booking.Adapter.SeatListAdapter
import com.example.ticket_booking.Adapter.TimeAdapter
import com.example.ticket_booking.Model.Film
import com.example.ticket_booking.Model.Seat
import com.example.ticket_booking.R
import com.example.ticket_booking.databinding.ActivitySeatListBinding
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class SeatListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeatListBinding
    private lateinit var film:Film
    private var price:Double=0.0
    private var number:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )


        getIntentExtra()
        setVariable()
        initSeatsList()
    }

    private fun initSeatsList() {
        val gridLayoutManager = GridLayoutManager(this, 7)
        gridLayoutManager.spanSizeLookup = object  : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return if(position % 7 ==3)1 else 1
            }
        }

        binding.seatRecyclerView.layoutManager = gridLayoutManager

        val seatList = mutableListOf<Seat>()
        val numberSeats = 81

        for (i in 0 until numberSeats){
            val SeatName=""
            val SeatStatus=if(i==2 || i==20 || i==33 || i==41 || i==50 || i==72 || i==73)Seat.SeatStatus.UNAVAILABLAE else Seat.SeatStatus.AVAILABLE

            seatList.add(Seat(SeatStatus, SeatName))
        }

        val seatAdapter = SeatListAdapter(seatList, this, object :SeatListAdapter.SelectedSeat{
            override fun Return(selectedName: String, num: Int) {
                binding.numberSelectedText.text = "$num Seat Selected"
                val df=DecimalFormat("#.##")
                price = df.format(num*film.price).toDouble()
                number=num
                binding.priceText.text = "$$price"
            }
        })

        binding.seatRecyclerView.adapter = seatAdapter

        binding.seatRecyclerView.isNestedScrollingEnabled = false

        binding.timeRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.timeRecyclerView.adapter = TimeAdapter(generateTimeSlots())

        binding.dateRecyclerView.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.dateRecyclerView.adapter = DateAdapter(generateDates())


    }

    private fun setVariable() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun getIntentExtra() {
        film = intent.getParcelableExtra("film")!!
    }

    private fun generateTimeSlots():List<String>{
        val timeSlots = mutableListOf<String>()
        val formatter = DateTimeFormatter.ofPattern("hh:mm a")

        for (i in 0 until 24 step 2){
            val time = LocalTime.of(i,0)
            timeSlots.add(time.format(formatter))
        }
        return  timeSlots
    }

    private fun generateDates():List<String>{
        val dates = mutableListOf<String>()
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("EEE/dd/MMM")

        for(i in 0 until 7){
            val date = today.plusDays(i.toLong())
            dates.add(date.format(formatter))
        }
        return dates
    }
}