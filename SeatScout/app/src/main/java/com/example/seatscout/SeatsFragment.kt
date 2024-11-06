package com.example.seatscout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seatscout.databinding.FragmentSeatsBinding

class SeatsFragment : Fragment() {
    var binding: FragmentSeatsBinding? = null
    private val args: SeatsFragmentArgs by navArgs()

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
        val adapter = SeatAdapter(getSeats(args.stadiumId))
        recyclerView?.adapter = adapter
    }

    private fun getSeats(stadiumId: Int): Array<Seat> {
        // stadiumId에 따라 다른 좌석 정보를 반환
        return when (stadiumId) {
            1 -> arrayOf(
                Seat("잠실 1루 내야지정석", 16000),
                Seat("잠실 3루 내야지정석", 16000),
                Seat("잠실 외야 일반석", 9000)
            )
            2 -> arrayOf(
                Seat("고척 1루 내야지정석", 15000),
                Seat("고척 3루 내야지정석", 15000),
                Seat("고척 외야 일반석", 8000)
            )
            // 다른 구장들에 대한 좌석 정보도 추가
            else -> arrayOf(
                Seat("1루 내야지정석", 15000),
                Seat("3루 내야지정석", 15000),
                Seat("외야 일반석", 8000)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}