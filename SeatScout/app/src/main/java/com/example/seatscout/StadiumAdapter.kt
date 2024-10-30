package com.example.seatscout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seatscout.databinding.ListStadiumBinding

class StadiumAdapter(val stadiums: Array<Stadium>) : RecyclerView.Adapter<StadiumAdapter.StadiumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StadiumViewHolder {
        val binding = ListStadiumBinding.inflate(LayoutInflater.from(parent.context), parent, false) // ViewGroup 지정, 새로 생성된 뷰를 부모에 즉시 붙이지 않도록 지정
        return StadiumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StadiumViewHolder, position: Int) {
        holder.bind(stadiums[position]) //캡슐화
    }

    override fun getItemCount() = stadiums.size

    class StadiumViewHolder(private val binding: ListStadiumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(stadium: Stadium) {
            binding.stadiumNameTextView.text = stadium.name
        }
    }
}