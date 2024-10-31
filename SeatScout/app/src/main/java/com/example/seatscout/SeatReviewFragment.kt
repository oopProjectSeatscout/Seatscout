package com.example.seatscout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seatscout.databinding.FragmentSeatReviewBinding

class SeatReviewFragment : Fragment() {
    var binding: FragmentSeatReviewBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSeatReviewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    fun setupRecyclerView() {
        val recyclerView = binding?.reviewRecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context) // 세로 방향 리스트로 설정
        val adapter = ReviewAdapter(getReviews())
        recyclerView?.adapter = adapter
    }

    fun getReviews(): Array<Review> {
        return arrayOf(
            Review(4.0f, "시야가 좋고 편안한 좌석이에요!"),
            Review(4.0f, "시야가 좋고 편안한 좌석이에요!"),
            Review(5.0f, "조금 좁지만 시야는 괜찮아요.")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}