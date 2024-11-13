package com.example.seatscout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seatscout.databinding.ItemSeatBinding

class SeatAdapter(private val seats: Array<Seat>, private val onClick: (Seat) -> Unit) : RecyclerView.Adapter<SeatAdapter.SeatViewHolder>() {

    inner class SeatViewHolder(private val binding: ItemSeatBinding, private val onClick: (Seat) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(seat: Seat) {
            binding.seatNameTextView.text = seat.name
            binding.reviewCountTextView.text = "리뷰 ${seat.reviewCount}개"
            binding.root.setOnClickListener { onClick(seat) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val binding = ItemSeatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeatViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        holder.bind(seats[position])
    }

    override fun getItemCount(): Int = seats.size
}