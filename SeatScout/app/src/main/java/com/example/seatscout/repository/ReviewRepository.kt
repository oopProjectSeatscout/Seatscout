package com.example.seatscout.repository

import androidx.lifecycle.MutableLiveData
import com.example.seatscout.model.Review
import com.google.firebase.database.*

class ReviewRepository {
    private val database = FirebaseDatabase.getInstance()
    private val userRef: DatabaseReference = database.getReference("reviews")

    // 특정 stadiumId에 해당하는 리뷰를 가져오는 메서드
    fun getReviews(stadiumId: Int, onSuccess: (List<Review>) -> Unit, onFailure: (Exception) -> Unit) {
        userRef.orderByChild("stadiumId").equalTo(stadiumId.toDouble()) // Int를 Double로 변환하여 비교
            .addListenerForSingleValueEvent(object : ValueEventListener {
                //성공적
                override fun onDataChange(snapshot: DataSnapshot) {
                    val reviewList = mutableListOf<Review>()
                    for (child in snapshot.children) {
                        val review = child.getValue(Review::class.java)
                        review?.let { reviewList.add(it) }
                    }
                    onSuccess(reviewList) // 성공 시 콜백 호출
                }
                
                //실패
                override fun onCancelled(error: DatabaseError) {
                    onFailure(Exception(error.message)) // 실패 시 콜백 호출
                }
            })
    }

    // 모든 stadiumId에 해당하는 리뷰를 가져오는 메서드
    fun getAllReviews(stadiumId: Int, onSuccess: (List<Review>) -> Unit, onFailure: (Exception) -> Unit) {
        userRef.orderByChild("stadiumId").equalTo(stadiumId.toDouble())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                // 성공적
                override fun onDataChange(snapshot: DataSnapshot) {
                    val reviewList = mutableListOf<Review>()
                    for (child in snapshot.children) {
                        val review = child.getValue(Review::class.java)
                        review?.let { reviewList.add(it) }
                    }
                    onSuccess(reviewList) // 성공 시 콜백 호출
                }

                // 실패
                override fun onCancelled(error: DatabaseError) {
                    onFailure(Exception(error.message)) // 실패 시 콜백 호출
                }
            })
    }



    // 리뷰를 Firebase에 제출하는 메서드
    fun submitReview(review: Review, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        userRef.push().setValue(review)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }
}
