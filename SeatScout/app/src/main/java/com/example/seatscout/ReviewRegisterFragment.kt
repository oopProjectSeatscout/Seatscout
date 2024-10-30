package com.example.seatscout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewRegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewRegisterFragment : Fragment() {
    private lateinit var ratingBar: RatingBar
    private lateinit var reviewContentEditText: EditText
    private lateinit var tagOptionsRadioGroup: RadioGroup
    private lateinit var submitReviewButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃을 인플레이트합니다.
        val view = inflater.inflate(R.layout.fragment_review_register, container, false)

        // UI 요소 초기화
        ratingBar = view.findViewById(R.id.ratingBar)
        reviewContentEditText = view.findViewById(R.id.reviewContentEditText)
        tagOptionsRadioGroup = view.findViewById(R.id.tagOptionsRadioGroup)
        submitReviewButton = view.findViewById(R.id.submitReviewButton)

        // 버튼 클릭 리스너 설정
        submitReviewButton.setOnClickListener {
            submitReview()
        }

        return view
    }

    private fun submitReview() {
        // 사용자 입력 값 가져오기
        val rating = ratingBar.rating
        val reviewContent = reviewContentEditText.text.toString()
        val selectedTagId = tagOptionsRadioGroup.checkedRadioButtonId

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
}