package com.example.seatscout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seatscout.databinding.FragmentReviewRegisterBinding
import android.widget.Toast

class ReviewRegisterFragment : Fragment() {

    private var binding: FragmentReviewRegisterBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReviewRegisterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding?.submitReviewButton?.setOnClickListener {
            submitReview()
        }
        binding?.uploadPhotoButton?.setOnClickListener {
            uploadPhoto()
        }
    }
    private fun submitReview() {
        val rating = binding?.ratingBar?.rating ?: 0f
        val seatLocation = binding?.seatLocationText?.text.toString()
        val reviewContent = binding?.reviewContentText?.text.toString()
        val selectTagId = binding?.tagOptionsRadioGroup?.checkedRadioButtonId

        val selectTag = when (selectTagId) {
            R.id.tagOption1 -> "시야 좋은"
            R.id.tagOption2 -> "시야 가림"
            R.id.tagOption3 -> "편안한 관람"
            R.id.tagOption4 -> "가볍다"
            R.id.tagOption5 -> "중간 정도"
            else -> "태그 없음"
        }

        if (reviewContent.isNotBlank() && seatLocation.isNotBlank()) {
            // 리뷰 제출 로직 추가 필요
            Toast.makeText(requireContext(), "리뷰가 제출되었습니다: $rating, $seatLocation, $selectTag", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "좌석위치와 리뷰내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadPhoto() {
        // 사진 업로드 로직 추가 필요
        Toast.makeText(requireContext(), "사진 업로드 완료", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
