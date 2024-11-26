package com.example.seatscout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seatscout.databinding.FragmentSeatReviewBinding
import androidx.navigation.fragment.navArgs
import androidx.navigation.fragment.findNavController
import com.example.seatscout.model.Review
import com.example.seatscout.repository.ReviewRepository
import com.example.seatscout.viewmodel.ReviewViewModel
import androidx.lifecycle.ViewModelProvider

class SeatReviewFragment : Fragment() {
    private var binding: FragmentSeatReviewBinding? = null
    private val args: SeatReviewFragmentArgs by navArgs()
    private lateinit var reviewViewModel: ReviewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSeatReviewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 설정
        setupRecyclerView()

        // Repository 초기화
        val repository = ReviewRepository()
        reviewViewModel = ReviewViewModel(repository)

        // 좌석 이름 설정
        binding?.seatNameTextView?.text = args.seatName

        // 리뷰 가져오기
        fetchReviews()

        // 리뷰 작성 버튼 설정
        setupReviewButton()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding?.reviewRecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context) // 세로 방향 리스트로 설정
    }

    private fun fetchReviews() {
        reviewViewModel.fetchReviews(args.stadiumId, args.seatName) // stadiumId와 seatName으로 리뷰 가져오기
        reviewViewModel.reviews.observe(viewLifecycleOwner) { reviews ->
            // Adapter에 리뷰 데이터 설정
            val adapter = ReviewAdapter(reviews)
            binding?.reviewRecyclerView?.adapter = adapter

            // 평균 평점 계산
            val averageRating = calculateAverageRating(reviews)
            binding?.averageRatingValueTextView?.text = String.format("★ %.1f / 5.0", averageRating) // 평균 평점 표시
        }
    }

    private fun calculateAverageRating(reviews: List<Review>): Float {
        return if (reviews.isEmpty()) {
            0.0f // 리뷰가 없을 경우 0 반환
        } else {
            reviews.map { it.rating }.average().toFloat() // 평점의 평균 계산
        }
    }


    private fun setupReviewButton() {
        binding?.writeReviewButton?.setOnClickListener {
            navigateToReviewRegister()
        }
    }

    private fun navigateToReviewRegister() {
        val action = SeatReviewFragmentDirections.actionSeatReviewFragmentToReviewRegisterFragment(
            stadiumId = args.stadiumId,
            seatName = args.seatName
        )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}