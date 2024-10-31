package com.example.seatscout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seatscout.databinding.FragmentSeatsBinding

class SeatsFragment : Fragment() {
    var binding: FragmentSeatsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSeatsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    fun setupRecyclerView() {
        val recyclerView = binding?.seatRecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context) // 세로 방향 리스트로 설정
        val adapter = SeatAdapter(getSeats())
        recyclerView?.adapter = adapter
    }

    fun getSeats(): Array<Seat> {
        return arrayOf(
            Seat("1루 내야지정석", 23),
            Seat("3루 내야지정석", 23),
            Seat("외야 일반석", 23)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}