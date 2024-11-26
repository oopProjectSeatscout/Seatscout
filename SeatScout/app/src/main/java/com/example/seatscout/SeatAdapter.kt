package com.example.seatscout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seatscout.databinding.ItemSeatBinding

class SeatAdapter(private var seats: Array<Seat>, private val clickListener: (Seat) -> Unit) : RecyclerView.Adapter<SeatAdapter.SeatViewHolder>() {

    inner class SeatViewHolder(private val binding: ItemSeatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(seat: Seat) {
            binding.seatNameTextView.text = seat.name
            binding.reviewCountTextView.text = "리뷰 개수: ${seat.reviewCount}"
            binding.root.setOnClickListener { clickListener(seat) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val binding = ItemSeatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        holder.bind(seats[position])
    }

    override fun getItemCount(): Int = seats.size

    // 특정 좌석의 리뷰 개수만 업데이트하는 메서드
    fun updateSeatReviewCount(seatName: String, newCount: Int) {
        val index = seats.indexOfFirst { it.name == seatName }
        if (index != -1) {
            seats[index].reviewCount = newCount
            notifyItemChanged(index) // 특정 항목만 업데이트
        }
    }
}
