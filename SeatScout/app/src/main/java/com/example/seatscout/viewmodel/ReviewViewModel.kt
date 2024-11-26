package com.example.seatscout.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.seatscout.model.Review
import com.example.seatscout.repository.ReviewRepository

class ReviewViewModel(private val repository: ReviewRepository) : ViewModel() {
    private val _submissionStatus = MutableLiveData<String>()
    val submissionStatus: LiveData<String> get() = _submissionStatus

    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>> get() = _reviews

    // 특정 seatName으로 필터링하여 리뷰 목록을 Firebase에서 가져오는 메서드
    fun fetchReviews(stadiumId: Int, seatName: String) {
        repository.getReviews(stadiumId, onSuccess = { reviewsList ->
            // seatName으로 필터링
            val filteredReviews = reviewsList.filter { it.seatName == seatName }
            _reviews.value = filteredReviews
        }, onFailure = { exception ->
            _reviews.value = emptyList() // 오류 발생 시 빈 리스트 설정
        })
    }

    fun fetchAllReviews(stadiumId: Int) {
        // Firebase에서 모든 리뷰를 가져오는 로직
        repository.getAllReviews(stadiumId, onSuccess = { fetchedReviews ->
            _reviews.value = fetchedReviews // 모든 리뷰를 LiveData에 설정
        }, onFailure = { exception ->
            Log.e("ReviewViewModel", "Error fetching reviews: ${exception.message}")
        })
    }

    // 리뷰를 제출하는 메서드
    fun submitReview(review: Review) {
        repository.submitReview(
            review = review,
            onSuccess = { _submissionStatus.value = "리뷰가 제출되었습니다." },
            onFailure = { exception -> _submissionStatus.value = "리뷰 제출에 실패하였습니다: ${exception.message}" }
        )
    }
}
