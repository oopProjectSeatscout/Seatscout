package com.example.seatscout.viewmodel

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

    init {
        repository.observeReviews(_reviews)
    }

    fun submitReview(review: Review) {
        repository.submitReview(
            review = review,
            onSuccess = { _submissionStatus.value = "리뷰가 제출되었습니다." },
            onFailure = { exception -> _submissionStatus.value = "리뷰 제출에 실패하였습니다: ${exception.message}" }
        )
    }
}
