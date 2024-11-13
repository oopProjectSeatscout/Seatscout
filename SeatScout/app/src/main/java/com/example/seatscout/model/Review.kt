package com.example.seatscout.model

data class Review(
    val rating: Float = 0f,
    val seatLocation: String = "",
    val reviewContent: String = "",
    val tag: String = ""
)
