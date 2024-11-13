package com.example.seatscout.repository

import androidx.lifecycle.MutableLiveData
import com.example.seatscout.model.Review
import com.google.firebase.database.*

class ReviewRepository {
    private val database = FirebaseDatabase.getInstance()
    private val userRef: DatabaseReference = database.getReference("reviews")

    fun observeReviews(reviews: MutableLiveData<List<Review>>) {
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val reviewList = mutableListOf<Review>()
                for (child in snapshot.children) {
                    val review = child.getValue(Review::class.java)
                    review?.let { reviewList.add(it) }
                }
                reviews.postValue(reviewList)
            }

            override fun onCancelled(error: DatabaseError) {
                // 오류 처리
            }
        })
    }

    fun submitReview(review: Review, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        userRef.push().setValue(review)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }
}
