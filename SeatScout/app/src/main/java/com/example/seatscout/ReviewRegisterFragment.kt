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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewRegisterBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.submitReviewButton?.setOnClickListener {
            submitReview()
        }
    }

    private fun submitReview() {
        // 사용자 입력 값 가져오기
        val rating = binding?.ratingBar?.rating ?: 0f
        val reviewContent = binding?.reviewContentEditText?.text.toString()
        val selectedTagId = binding?.tagOptionsRadioGroup?.checkedRadioButtonId

        // 선택된 태그 확인
        val selectedTag = when (selectedTagId) {
            R.id.tagOption1 -> "시야 좋은"
            R.id.tagOption2 -> "그룹"
            R.id.tagOption3 -> "편안한 관람"
            R.id.tagOption4 -> "가볍다"
            R.id.tagOption5 -> "중간 정도"
            else -> "태그 없음"
        }

        // 입력값 확인 및 처리 (예: 서버에 전송)
        if (reviewContent.isNotBlank()) {
            // 리뷰 제출 로직을 여기에 추가
            Toast.makeText(requireContext(), "리뷰가 제출되었습니다: $rating, $selectedTag", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "리뷰 내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
