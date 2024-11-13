package com.example.seatscout.model

data class Review(
    val stadiumId: Int = 0,    // 경기장 ID
    val seatName: String = "",      // 좌석 이름
    val seatLocation: String = "",   // 좌석 위치
    val rating: Float = 0f,          // 평점
    val content: String = "",        // 리뷰 내용
    val tag: String = ""             // 태그
)
